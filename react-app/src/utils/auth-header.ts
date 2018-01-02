export function authHeader():Headers {
    var myHeaders = new Headers();
    let user = JSON.parse(localStorage.getItem('user'));
    if (user && user.token) {
        myHeaders.set('Authorization', 'Bearer ' + user.token);
    }
    return myHeaders;
}