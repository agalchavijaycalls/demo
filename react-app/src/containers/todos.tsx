import * as React from 'react';
import * as TodoActions from '../redux/todo/actions';
import {bindActionCreators} from 'redux';
import {connect} from 'react-redux';
import {RouteComponentProps} from 'react-router';
import {RootState} from '../redux';
import {TodoService} from '../services/todo-service';
import {FILTER_TYPES, SHOW_ACTIVE, SHOW_ALL, SHOW_COMPLETED} from '../constants/filters';

export namespace TodoApp {
    export interface Props extends RouteComponentProps<void> {
        todos: TodoItemData[];
        actions: typeof TodoActions;
    }

    export interface State {
        /* empty */
    }
}

@connect(mapStateToProps, mapDispatchToProps)
export class TodoApp extends React.Component<TodoApp.Props, TodoApp.State> {
    private todoService: TodoService;

    componentDidMount(): void {
        this.todoService = TodoService.getInstance();
        this.todoService.getTodosWithTryCatch().then(value => {
            this.props.actions.resetWithTodos(value);
        }).catch(error => {
            alert(error);
        });
    }

    render() {
        const {todos, actions, children} = this.props;
        return (
            <div className={'normal'}>
                <Header addTodo={actions.addTodo}/>
                <MainSection todos={todos} actions={actions}/>
                {children}
            </div>
        );
    }
}

function mapStateToProps(state: RootState) {
    return {
        todos: state.todos
    };
}

function mapDispatchToProps(dispatch: any) {
    return {
        actions: bindActionCreators(TodoActions as any, dispatch)
    };
}

const TODO_FILTERS = {
    [SHOW_ALL]: () => true,
    [SHOW_ACTIVE]: (todo: TodoItemData) => !todo.completed,
    [SHOW_COMPLETED]: (todo: TodoItemData) => todo.completed
};

export namespace MainSection {
    export interface Props {
        todos: TodoItemData[];
        actions: typeof TodoActions;
    }

    export interface State {
        filter: TodoFilterType;
    }
}

export class MainSection extends React.Component<MainSection.Props, MainSection.State> {

    constructor(props?: MainSection.Props, context?: any) {
        super(props, context);
        this.state = {filter: SHOW_ALL};
        this.handleClearCompleted = this.handleClearCompleted.bind(this);
        this.handleShow = this.handleShow.bind(this);
    }

    handleClearCompleted() {
        const atLeastOneCompleted = this.props.todos.some(todo => todo.completed);
        if (atLeastOneCompleted) {
            this.props.actions.clearCompleted();
        }
    }

    handleShow(filter: TodoFilterType) {
        this.setState({filter});
    }

    renderToggleAll(completedCount: number) {
        const {todos, actions} = this.props;
        if (todos.length > 0) {
            return (
                <input
                    className={'toggleAll'}
                    type="checkbox"
                    checked={completedCount === todos.length}
                    onChange={actions.completeAll}/>
            );
        } else {
            return null;
        }
    }

    renderFooter(completedCount: number) {
        const {todos} = this.props;
        const {filter} = this.state;
        const activeCount = todos.length - completedCount;

        if (todos.length) {
            return (
                <Footer filter={filter}
                        activeCount={activeCount}
                        completedCount={completedCount}
                        onClearCompleted={this.handleClearCompleted}
                        onShow={this.handleShow}/>
            );
        } else {
            return null;
        }
    }

    render() {
        const {todos, actions} = this.props;
        const {filter} = this.state;

        const filteredTodos = todos.filter(TODO_FILTERS[filter]);
        const completedCount = todos.reduce((count, todo) => {
            return todo.completed ? count + 1 : count;
        }, 0);

        return (
            <section className={'main'}>
                {this.renderToggleAll(completedCount)}
                <ul className={'normal'}>
                    {filteredTodos.map(todo =>
                        <TodoItem
                            key={todo.id}
                            todo={todo}
                            completeTodo={actions.completeTodo}
                            deleteTodo={actions.deleteTodo}
                            editTodo={actions.editTodo}/>
                    )}
                </ul>
                {this.renderFooter(completedCount)}
            </section>
        );
    }
}


export const FILTER_TITLES = {
    [SHOW_ALL]: 'All',
    [SHOW_ACTIVE]: 'Active',
    [SHOW_COMPLETED]: 'Completed'
};

export namespace Footer {
    export interface Props {
        filter: TodoFilterType;
        activeCount: number;
        completedCount: number;
        onShow: (filter: TodoFilterType) => any;
        onClearCompleted: () => any;
    }

    export interface State {
        /* empty */
    }
}

export class Footer extends React.Component<Footer.Props, Footer.State> {

    renderTodoCount() {
        const {activeCount} = this.props;
        const itemWord = activeCount === 1 ? 'item' : 'items';

        return (
            <span className={'count'}>
        <strong>{activeCount || 'No'}</strong> {itemWord} left
      </span>
        );
    }

    renderFilterLink(filter: TodoFilterType) {
        const {filter: selectedFilter, onShow} = this.props;

        return (
            <a className={filter === selectedFilter ? 'selected ' : ' '}
               style={{cursor: 'pointer'}}
               onClick={() => onShow(filter)}>
                {FILTER_TITLES[filter]}
            </a>
        );
    }

    renderClearButton() {
        const {completedCount, onClearCompleted} = this.props;
        if (completedCount > 0) {
            return (
                <button className={'clearCompleted'} onClick={onClearCompleted}>
                    Clear completed
                </button>
            );
        } else {
            return null;
        }
    }

    render() {
        return (
            <footer className={'normal'}>
                {this.renderTodoCount()}
                <ul className={'filters'}>
                    {FILTER_TYPES.map((filter) =>
                        <li key={filter}>
                            {this.renderFilterLink(filter)}
                        </li>
                    )}
                </ul>
                {this.renderClearButton()}
            </footer>
        );
    }
}

export namespace TodoItem {
    export interface Props {
        todo: TodoItemData;
        editTodo: (todo: TodoItemData) => any;
        deleteTodo: (id: number) => any;
        completeTodo: (id: number) => any;
    }

    export interface State {
        editing: boolean;
    }
}

export class TodoItem extends React.Component<TodoItem.Props, TodoItem.State> {

    constructor(props?: TodoItem.Props, context?: any) {
        super(props, context);
        this.state = {
            editing: false
        };
        this.handleSave = this.handleSave.bind(this);
        this.handleDoubleClick = this.handleDoubleClick.bind(this);
    }

    handleDoubleClick() {
        this.setState({editing: true});
    }

    handleSave(id: number, title: string) {
        if (title.length === 0) {
            this.props.deleteTodo(id);
        } else {
            this.props.editTodo({id, title});
        }
        this.setState({editing: false});
    }

    render() {
        const {todo, completeTodo, deleteTodo} = this.props;

        let element;
        if (this.state.editing) {
            element = (
                <TodoTextInput title={todo.title}
                               editing={this.state.editing}
                               onSave={(title) => this.handleSave(todo.id, title)}/>
            );
        } else {
            element = (
                <div className={'view'}>
                    <input className={'toggle'}
                           type="checkbox"
                           checked={todo.completed}
                           onChange={() => completeTodo(todo.id)}/>

                    <label onDoubleClick={this.handleDoubleClick}>
                        {todo.title}
                    </label>

                    <button className={'destroy'} onClick={() => deleteTodo(todo.id)}/>
                </div>
            );
        }

        return (
            <li className={todo.completed ? ' completed ' : ' ' + this.state.editing ? ' editing ' : ' ' + !this.state.editing ? ' normal ' : ' '}>
                {element}
            </li>
        );
    }
}


export namespace Header {
    export interface Props {
        addTodo: (todo: TodoItemData) => any;
    }

    export interface State {
        /* empty */
    }
}

export class Header extends React.Component<Header.Props, Header.State> {

    constructor(props?: Header.Props, context?: any) {
        super(props, context);
        this.handleSave = this.handleSave.bind(this);
    }

    handleSave(title: string) {
        if (title.length) {
            this.props.addTodo({title});
        }
    }

    render() {
        return (
            <header>
                <h1>Todos</h1>
                <TodoTextInput
                    newTodo
                    onSave={this.handleSave}
                    placeholder="What needs to be done?"/>
            </header>
        );
    }
}

export namespace TodoTextInput {
    export interface Props {
        title?: string;
        placeholder?: string;
        newTodo?: boolean;
        editing?: boolean;
        onSave: (title: string) => any;
    }

    export interface State {
        title: string;
    }
}

export class TodoTextInput extends React.Component<TodoTextInput.Props, TodoTextInput.State> {

    constructor(props?: TodoTextInput.Props, context?: any) {
        super(props, context);
        this.state = {
            title: this.props.title || ''
        };
        this.handleBlur = this.handleBlur.bind(this);
        this.handleSubmit = this.handleSubmit.bind(this);
        this.handleChange = this.handleChange.bind(this);
    }

    handleSubmit(e: any) {
        const title = e.target.value.trim();
        if (e.which === 13) {
            this.props.onSave(title);
            if (this.props.newTodo) {
                this.setState({title: ''});
            }
        }
    }

    handleChange(e: any) {
        this.setState({title: e.target.value});
    }

    handleBlur(e: any) {
        const title = e.target.value.trim();
        if (!this.props.newTodo) {
            this.props.onSave(title);
        }
    }

    render() {

        return (
            <input className={'normal ' + this.props.editing ? 'edit ' : ' ' + this.props.newTodo ? ' new' : ' '}
                   type="title"
                   autoFocus
                   placeholder={this.props.placeholder}
                   value={this.state.title}
                   onBlur={this.handleBlur}
                   onChange={this.handleChange}
                   onKeyDown={this.handleSubmit}/>
        );
    }
}
