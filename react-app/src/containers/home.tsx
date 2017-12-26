import * as React from 'react';
import {Route, Switch} from 'react-router';
import {Link} from 'react-router-dom';
import {CounterContainer} from "./counter";
import {TodoApp} from './todos';

export const Home = () => {
    return (
        <section>
            <br/>
            <h3>
                <Link to={'/counter'}>Counter</Link>
                <Link to={'/todos'}>Services</Link>
            </h3>
            <Switch>
                <Route exact path='/counter' component={CounterContainer}/>
                <Route exact path='/todos' component={TodoApp}/>
            </Switch>
        </section>
    );
};