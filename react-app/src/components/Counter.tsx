import * as React from 'react';
import {Actions} from '../redux/counters';
import {connect} from 'react-redux';
import {bindActionCreators} from 'redux';
import {RootState} from '../redux';

export interface StatefulCounterProps {
    label: string,
    count?: number,
    actions?: typeof Actions;
}

@connect(mapStateToProps, mapDispatchToProps)
export class Counter extends React.Component<StatefulCounterProps, any> {

    render() {
        const {actions} = this.props;
        const {label} = this.props;
        const {count} = this.props;

        return (
            <div>
                {label}: {count}
                <button type="button" onClick={actions ? actions.increment : () => {
                }}>
                    {`Increment`}
                </button>
            </div>
        );
    }
}


function mapStateToProps(state: RootState) {
    return {
        count: state.counters.counter
    };
}

function mapDispatchToProps(dispatch: any) {
    return {
        actions: bindActionCreators(Actions as any, dispatch)
    };
}