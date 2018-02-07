export interface Country extends Serializable {
  code: string;
  version: number;
  name: string;
  isoCode: string;
  numCode: number;
  dateCreated: Date;
  comment: string;
  provinces: Province[];
}

export interface CurrencyType extends Serializable {
  code: string;
  version: number;
  name: string;
  dateCreated: Date;
  comment: string;
}

export interface InstituteType extends Serializable {
  code: string;
  version: number;
  name: string;
  dateCreated: Date;
  comment: string;
}

export interface PayFrequency extends Serializable {
  code: string;
  name: string;
  version: number;
  dateCreated: Date;
  comment: string;
}

export interface PayrollComponent extends Serializable {
  code: string;
  version: number;
  name: string;
  contributionType: PayContributionType;
  payrollComponentType: PayrollComponentType;
  comment: string;
  dateCreated: Date;
}

export interface PayrollComponentType extends Serializable {
  code: string;
  version: number;
  name: string;
  comment: string;
  dateCreated: Date;
}

export interface Permission extends Serializable {
  code: string;
  version: number;
  name: string;
  dateCreated: Date;
  comment: string;
}

export interface Province extends Serializable {
  id: number;
  version: number;
  code: string;
  name: string;
  country: Country;
  dateCreated: Date;
  comment: string;
}

export interface Role extends Serializable {
  code: string;
  version: number;
  name: string;
  dateCreated: Date;
  comment: string;
  rolePermissions: RolePermission[];
}

export interface RolePermission extends Serializable {
  id: number;
  version: number;
  role: Role;
  permission: Permission;
  status: RolePermissionStatus;
  statusReason: string;
  dateStatusUpdated: Date;
  dateCreated: Date;
  comment: string;
}

export interface Timezone extends Serializable {
  code: string;
  version: number;
  name: string;
  offsetValue: number;
  dateCreated: Date;
  comment: string;
}

export type PayContributionType = 'EARNING' | 'DEDUCTION';

export  type RolePermissionStatus = 'PENDING' | 'ACTIVE';

export interface Serializable {
}