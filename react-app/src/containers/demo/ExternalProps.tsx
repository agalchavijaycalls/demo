import { History } from 'history';

export interface ExternalProps {
  style?: string;
}

export interface InjectedProps {
  clickCount: number;
}

export interface State {
  clickCount: number;
}

export interface Options {
  debug?: boolean;
  historya?: History;
}