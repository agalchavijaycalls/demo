
export class TodoService {
    private static instance: TodoService;

    private constructor() {

    }

    public static getInstance() {
        if (!TodoService.instance) {
            TodoService.instance = new TodoService();
        }
        return TodoService.instance;
    }

    async getTodos(): Promise<TodoItemData[]> {
        let response:Response = await fetch('http://localhost:8080/todos1');
        this.checkStatus(response);
        let todos = await response.json();
        console.log('response received');
        return todos;
    }

    getTodosWithTryCatch(): Promise<TodoItemData[]> {
        try {
            return this.getTodos();
        } catch (e) {
            console.log('Exception while todo fetch');
            console.log(e);
            throw e;
        }
    }

    getTodosUsingThen():Promise<TodoItemData[]>  {
        return fetch('http://localhost:8080/todos1')
            .then((response: any) => {
                if(response.ok) {
                    return response.json();
                }else{
                    throw new Error('Network error');
                }
            })
            .then((person: any) => {
                return person;
            })
            .catch((e: any) => {
               throw e;
            });
    }

    private checkStatus(response : Response) : Promise<Response> {
        if (response.status >= 200 && response.status < 300) {
            return Promise.resolve(response);
        } else {
            throw new Error(response.status.toString());
        }
    }
}