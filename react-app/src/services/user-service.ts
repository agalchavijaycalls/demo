
export const userService = {
    login
};

function login(userName:string, password:string){
    var myHeaders = new Headers()
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