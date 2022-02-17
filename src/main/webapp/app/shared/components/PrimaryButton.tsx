import React from 'react';
import { Translate } from 'react-jhipster';
import { Link } from 'react-router-dom';
import { Button } from 'reactstrap';
import './components.scss';

export interface IPrimaryButtonProps {
  onClick?: () => void;
  style?: React.CSSProperties;
  text?: string;
  to?: string;
  translateKey?: string;
  className?: string;
}

export const PrimaryButton = (props: IPrimaryButtonProps) => {
  return (
    <Button onClick={props.onClick} style={props.style} className={props.className ? props.className : '.ms-Primary-Button'}>
      <Link to={props.to} style={{ color: props.style?.color ? props.style.color : 'white' }} className="ms-Primary-ButtonLink">
        <Translate contentKey={props.translateKey}>{props.text}</Translate>
      </Link>
    </Button>
  );
};
