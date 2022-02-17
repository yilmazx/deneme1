import './home.scss';
import React from 'react';
import { Translate } from 'react-jhipster';
import { Button } from 'reactstrap';
import { Link } from 'react-router-dom';
import { PrimaryButton } from 'app/shared/components/PrimaryButton';

export const TryItForFree = () => {
  return (
    <div className="tryFreePage-conatiner">
      <div className="tryFreePage-greeting">
        <h4>
          <Translate contentKey="home.greeting"> Hey, I am Maya</Translate>
        </h4>
        <h1>
          <Translate contentKey="home.whatwouldyoulike"> What would you like?</Translate>
        </h1>
      </div>
      <div className="tryFreePage-buttons-container">
        {/* <Button className="tryFree-buttons">
          <Link to={'/account/register'} style={{ color: 'black' }}>
            <Translate contentKey="home.creatdocument">Create Document</Translate>
          </Link>
        </Button> */}
        <PrimaryButton
          className="tryFree-buttons"
          text="Create Document"
          to="/account/register"
          translateKey="home.creatdocument"
          style={{ color: 'black' }}
        />
        <PrimaryButton
          className="tryFree-buttons"
          text="Upload and Sign your document"
          to="/account/register"
          translateKey="home.uploadandsign"
          style={{ color: 'black' }}
        />
      </div>
    </div>
  );
};
