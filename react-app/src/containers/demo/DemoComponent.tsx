import { clickCounted } from './clickCounted';
import * as React from 'react';
import { InjectedProps } from './ExternalProps';

interface DemoProps {
  text: string;
}

const DemoComponent = (props: DemoProps & InjectedProps): JSX.Element => {
  return (
      <div>
        <p>{props.text}</p>
        <p>
          {
            props.clickCount >= 5
                ? 'Easy there'
                : 'Bring It'
          }
        </p>
      </div>
  );
};

export const Demo = clickCounted()(DemoComponent);