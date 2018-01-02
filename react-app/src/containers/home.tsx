import * as React from 'react';
import {Route, Switch} from 'react-router';
import {Link} from 'react-router-dom';
import {CounterContainer} from "./counter";
import {TodoApp} from './todos';
import {Alert} from "./alert";
import {RootState} from "../redux";
import {Actions} from '../redux/alert';
import {connect} from "react-redux";
import {history} from './../store';
import {bindActionCreators} from "redux";

export interface Props {
    alert?: any,
    actions?: typeof Actions;
}

@connect(mapStateToProps, mapDispatchToProps)
export class Home extends React.Component<Props, any> {
    constructor(props:any) {
        super(props);

        history.listen((location, action) => {
            // clear alert on location change
            this.props.actions.clear();
        });
    }

    render() {
        const {alert} = this.props;
        return (
            <div className="container">
                <div className="col-sm-8 col-sm-offset-2">
                    {alert.message &&
                    <div className={`alert ${alert.type}`}>{alert.message}</div>
                    }
                    <br/>
                    <Link to={'/counter'}>Counter</Link>
                    <Link to={'/todos'}>Services</Link>
                    <Link to={'/alert'}>Alert</Link>
                    <Switch>
                        <Route exact path='/counter' component={CounterContainer}/>
                        <Route exact path='/todos' component={TodoApp}/>
                        <Route exact path='/alert' component={Alert}/>
                    </Switch>
                </div>
            </div>
        );
    }
};

function mapStateToProps(state: RootState) {
    return {
        alert: state.alert
    };
}

function mapDispatchToProps(dispatch: any) {
    return {
        actions: bindActionCreators(Actions as any, dispatch)
    };
}