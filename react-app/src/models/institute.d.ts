// Generated using typescript-generator version 1.14.251 on 2017-02-02 20:21:08.
import { InstituteType, Serializable } from './reference-data';
import { InstituteMember } from './user';

export interface Employee extends Serializable {
  instituteMemberId: number;
  version: number;
  code: string;
  instituteMember: InstituteMember;
  status: EmployeeStatus;
  statusReason: string;
  dateStatusUpdated: Date;
  dateCreated: Date;
  dateUpdated: Date;
  comment: string;
}

export interface Institute extends Serializable {
  code: string;
  version: number;
  name: string;
  type: InstituteType;
  status: InstituteStatus;
  statusReason: string;
  dateStatusUpdated: Date;
  dateCreated: Date;
  dateUpdated: Date;
  comment: string;
}

export type EmployeeStatus = 'PENDING' | 'ACTIVE';

export type InstituteStatus = 'PENDING' | 'ACTIVE';