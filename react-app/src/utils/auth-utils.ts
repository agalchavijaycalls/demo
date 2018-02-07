import { AUTH_CONSTANTS } from '../constants';

function getBearerAuthHeader(): Headers {
  var myHeaders = new Headers();
  let item = sessionStorage.getItem(AUTH_CONSTANTS.USER_TOKEN_KEY);
  let user;
  if (item != null) {
    user = JSON.parse(item);
  }
  if (user && user.token) {
    myHeaders.set(AUTH_CONSTANTS.AUTHORIZATION_HEADER_KEY, AUTH_CONSTANTS.BEARER_PREFIX + user.token);
  }
  return myHeaders;
}

export const authUtils = {
  getBearerAuthHeader: getBearerAuthHeader
};