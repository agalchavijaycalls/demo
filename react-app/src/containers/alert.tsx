import * as React from 'react';
import {Actions} from '../redux/alert';
import {connect} from 'react-redux';
import {bindActionCreators} from 'redux';
import {RootState} from '../redux';

export interface Props {
    alert: string,
    actions?: typeof Actions;
}

@connect(mapStateToProps, mapDispatchToProps)
export class Alert extends React.Component<Props, any> {

    render() {
        const {actions} = this.props;
        const {alert} = this.props;

        return (
            <div>
                {alert}
                <button type="button" onClick={() => {
                    actions ? actions.error('test') : null
                }}>
                    {`Error`}
                </button>
                <button type="button" onClick={() => {
                    actions ? actions.success('test') : null
                }}>
                    {`Error`}
                </button>
                <button type="button" onClick={() => {
                    actions ? actions.clear() : null
                }}>
                    {`Error`}
                </button>
            </div>
        );
    }
}


function mapStateToProps(state: RootState) {
    return {
        alert: state.alert.message
    };
}

function mapDispatchToProps(dispatch: any) {
    return {
        actions: bindActionCreators(Actions as any, dispatch)
    };
}