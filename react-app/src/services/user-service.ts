import {authHeader} from "../utils/auth-header";

function login(userName:string, password:string):any{
    var myHeaders = new Headers();
    myHeaders.set('Content-Type', 'application/json' );
    const requestOptions:RequestInit = {
        method: 'POST',
        headers: myHeaders,
        body: JSON.stringify({ userName, password })
    };

    return fetch('/users/authenticate', requestOptions)
        .then(response => {
            if (!response.ok) {
                return Promise.reject(response.statusText);
            }

            return response.json();
        })
        .then(user => {
            // login successful if there's a jwt token in the response
            if (user && user.token) {
                // store user details and jwt token in local storage to keep user logged in between page refreshes
                localStorage.setItem('user', JSON.stringify(user));
            }

            return user;
        });
}

function logout() {
    // remove user from local storage to log user out
    localStorage.removeItem('user');
}

function getAll() {
    const requestOptions = {
        method: 'GET',
        headers: authHeader()
    };

    return fetch('/users', requestOptions).then(handleResponse);
}

function getById(id:any) {
    const requestOptions = {
        method: 'GET',
        headers: authHeader()
    };

    return fetch('/users/' + id, requestOptions).then(handleResponse);
}

function register(user:any) {
    var myHeaders = new Headers();
    myHeaders.set('Content-Type', 'application/json' );
    const requestOptions:RequestInit = {
        method: 'POST',
        headers: myHeaders,
        body: JSON.stringify(user)
    };

    return fetch('/users/register', requestOptions).then(handleResponse);
}

function update(user:any) {
    let headers = authHeader();
    headers.set('Content-Type', 'application/json');
    const requestOptions = {
        method: 'PUT',
        headers: headers,
        body: JSON.stringify(user)
    };

    return fetch('/users/' + user.id, requestOptions).then(handleResponse);;
}

// prefixed function name with underscore because delete is a reserved word in javascript
function _delete(id:any) {
    const requestOptions = {
        method: 'DELETE',
        headers: authHeader()
    };

    return fetch('/users/' + id, requestOptions).then(handleResponse);;
}

function handleResponse(response:any) {
    if (!response.ok) {
        return Promise.reject(response.statusText);
    }

    return response.json();
}

export const userService = {
    login,
    logout,
    register,
    getAll,
    getById,
    update,
    delete: _delete
};