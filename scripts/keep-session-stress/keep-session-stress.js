const request = require("request");
const rxjs = require("rxjs");

const BASE_URL = "http://localhost:8080/hello"

const Observable = rxjs.Observable

const timer = Observable.timer(0, 10)


const NUMBER_OF_SESSION = 3
const NUMBER_OF_REQUEST_BY_SESSION = 100

let errors = []


const getCookies = () => {
    return Observable.create(obs =>
        request.get(`${BASE_URL}/setCookie`, (err, res, body) => {
            if(err){
                console.log("recap", err)
                obs.error(err);
            }
            try {
                const cookie = res.headers['Set-Cookie'] || res.headers['set-cookie']
                console.log("getCookies", cookie)
                obs.next(cookie)
            }catch(err){
                console.log("getCookies error", res.headers)
                console.log("getCookies error", body)
                errors.push(err)
                //obs.error(err)
            }
        })
    )
};

const duplicateEvents = (cookie) =>
    Observable.range(0, NUMBER_OF_REQUEST_BY_SESSION)
        .map(_ => cookie)

const testRequest = (cookie) => {
    return Observable.create(obs =>
        request.get({
            url: `${BASE_URL}/testCookie`,
            headers: {
                'Cookie': cookie
            }}, (err, res, body) => {
            if(err){
                console.log("recap", err)
                obs.error(err);
            }
            console.log("testRequest", cookie)
            obs.next(cookie)
        })
    )
};




timer
    .take(NUMBER_OF_SESSION)
    .flatMap(getCookies)
    .flatMap(duplicateEvents)
    .flatMap(testRequest)
    .subscribe(e => {
        console.log(e)
        console.log("errors", errors)
    })
