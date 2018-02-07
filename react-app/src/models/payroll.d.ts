import { Employee } from './institute';
import { CurrencyType, PayFrequency, PayrollComponent, Serializable } from './reference-data';

export interface EmployeeSalary extends Serializable {
  id: number;
  version: number;
  employee: Employee;
  currencyType: CurrencyType;
  name: string;
  dateCreated: Date;
  dateUpdated: Date;
  status: EmployeeSalaryStatus;
  statusReason: string;
  dateStatusUpdated: Date;
  comment: string;
  employeeSalaryItems: EmployeeSalaryItem[];
}

export interface EmployeeSalaryItem extends Serializable {
  id: number;
  version: number;
  employeeSalary: EmployeeSalary;
  payrollComponent: PayrollComponent;
  payFrequency: PayFrequency;
  amount: number;
  comment: string;
  dateCreated: Date;
}

export interface EmployeeSalarySlipTemplate extends Serializable {
  id: number;
  version: number;
  employee: Employee;
  payrollComponent: PayrollComponent;
  amount: number;
  columnOrder: number;
  comment: string;
  dateCreated: Date;
}

export interface SalarySlip extends Serializable {
  id: number;
  version: number;
  employee: Employee;
  currencyType: CurrencyType;
  name: string;
  dateStart: Date;
  dateEnd: Date;
  status: SalarySlipStatus;
  statusReason: string;
  dateStatusUpdated: Date;
  comment: string;
  dateCreated: Date;
  salarySlipItems: SalarySlipItem[];
}

export interface SalarySlipItem extends Serializable {
  id: number;
  version: number;
  salarySlip: SalarySlip;
  payrollComponent: PayrollComponent;
  amount: number;
  columnOrder: number;
  comment: string;
  dateCreated: Date;
}

export type EmployeeSalaryStatus = 'PENDING' | 'ACTIVE';

export type SalarySlipStatus = 'PENDING' | 'ACTIVE';

export type PayContributionType = 'EARNING' | 'DEDUCTION';
