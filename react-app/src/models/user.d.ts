import { Institute } from './institute';
import { Role, Serializable } from './reference-data';

interface InstituteMember extends Serializable {
  id: number;
  version: number;
  userInfo: UserInfo;
  institute: Institute;
  status: InstituteMemberStatus;
  statusReason: string;
  dateStatusUpdated: Date;
  dateCreated: Date;
  comment: string;
  instituteMemberRoles: InstituteMemberRole[];
}

interface InstituteMemberRole extends Serializable {
  id: number;
  version: number;
  instituteMember: InstituteMember;
  role: Role;
  status: InstituteMemberRoleStatus;
  statusReason: string;
  dateStatusUpdated: Date;
  dateCreated: Date;
  comment: string;
}

interface LoginInfo extends Serializable {
  userName: string;
  version: number;
  password: string;
  securityQuestion: string;
  securityAnswer: string;
  status: LoginStatus;
  statusReason: string;
  dateStatusUpdated: Date;
  dateCreated: Date;
  dateLastLogin: Date;
  datePasswordUpdated: Date;
  comment: string;
  userInfo: UserInfo;
}

interface UserInfo extends Serializable {
  userName: string;
  version: number;
  displayName: string;
  emailId: string;
  mobileNo: string;
  dateCreated: Date;
  dateUpdated: Date;
  comment: string;
  instituteMembers: InstituteMember[];
}

type InstituteMemberStatus = 'PENDING' | 'ACTIVE';

type InstituteMemberRoleStatus = 'PENDING' | 'ACTIVE';

type LoginStatus = 'PENDING' | 'ACTIVE';