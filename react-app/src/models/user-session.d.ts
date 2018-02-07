import { InstituteMember, UserInfo } from './user';

export interface UserSession {
  userInfo: UserInfo;
  currentWorkingInstituteMember: InstituteMember;
}