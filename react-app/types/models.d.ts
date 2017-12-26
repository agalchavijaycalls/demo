/** TodoMVC model definitions **/

declare interface TodoItemData {
  id?: TodoItemId;
  title?: string;
  completed?: boolean;
  order?:number;
}

declare type TodoItemId = number;

declare type TodoFilterType = 'SHOW_ALL' | 'SHOW_ACTIVE' | 'SHOW_COMPLETED';

declare type TodoStoreState = TodoItemData[];
