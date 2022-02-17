import './home.scss';

import React from 'react';
import { Link } from 'react-router-dom';
import { Translate } from 'react-jhipster';
import { Row, Col, Button, Alert } from 'reactstrap';

import { useAppSelector } from 'app/config/store';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { TryItForFree } from './TryItForFree';

export const Home = () => {
  const account = useAppSelector(state => state.authentication.account);
  const [isOpenTIFFPage, setIsOpenTIFFPage] = React.useState<boolean>(false);

  return (
    <Row>
      {!isOpenTIFFPage && (
        <>
          <Col md="12" className="homeCol">
            <FontAwesomeIcon icon="user" />
          </Col>
          <Col md="12" className="homeCol">
            <h4>
              <Translate contentKey="home.subtitle"> Create, download or sign a document or upload your document and Sign</Translate>
            </h4>

            {/* {account?.login ? (
          <div>
            <Alert color="success">
              <Translate contentKey="home.logged.message" interpolate={{ username: account.login }}>
                You are logged in as user {account.login}.
              </Translate>
            </Alert>
          </div>
        ) : (
          <div>
            <Alert color="warning">
              <Translate contentKey="global.messages.info.authenticated.prefix">If you want to </Translate>

              <Link to="/login" className="alert-link">
                <Translate contentKey="global.messages.info.authenticated.link"> sign in</Translate>
              </Link>
              <Translate contentKey="global.messages.info.authenticated.suffix">
                , you can try the default accounts:
                <br />- Administrator (login=&quot;admin&quot; and password=&quot;admin&quot;)
                <br />- User (login=&quot;user&quot; and password=&quot;user&quot;).
              </Translate>
            </Alert>

            <Alert color="warning">
              <Translate contentKey="global.messages.info.register.noaccount">You do not have an account yet?</Translate>&nbsp;
              <Link to="/account/register" className="alert-link">
                <Translate contentKey="global.messages.info.register.link">Register a new account</Translate>
              </Link>
            </Alert>
          </div>
        )} */}
          </Col>
          <Col md="12" className="padding1Percent">
            <Button color="primary" type="submit" data-cy="submit" className="homeButton" onClick={() => setIsOpenTIFFPage(true)}>
              <Translate contentKey="home.tryFree"> TRY IT FOR FREE</Translate>
            </Button>
          </Col>
          <Col md="12" className="padding1Percent">
            <Button color="primary" type="submit" data-cy="submit" className="homeButton">
              <b>
                <Translate contentKey="home.bookDemo"> BOOK A DEMO</Translate>
              </b>
            </Button>
          </Col>
          <Col md="12" className="homeCol margintop10Percent">
            <h2>
              <Translate contentKey="home.section.title">Our most popular documents </Translate>
            </h2>
            <br></br>
            <Row className="paddingLeft5Percent">
              <Col md="4">
                <h5 className="textAlignLeft">
                  <FontAwesomeIcon icon="building" className="orangeColor" />
                  <Translate contentKey="home.section.subsection1.title">Business And Contract</Translate>{' '}
                </h5>
                <br></br>
                <div className="orangeColor">
                  <Translate contentKey="home.section.subsection1.text1">Non-Disclosure Aggrement </Translate>
                </div>
                <div className="orangeColor">
                  <Translate contentKey="home.section.subsection1.text2">LLC Operation Aggrement </Translate>
                </div>
                <div className="orangeColor">
                  <Translate contentKey="home.section.subsection1.text3">Independent Contractor Aggrement </Translate>
                </div>
                <div className="orangeColor">
                  <Translate contentKey="home.section.subsection1.text4">Business Aggrement</Translate>
                </div>
                <br></br>
                <br></br>
                <div className="orangeColor">
                  <h5>
                    <Translate contentKey="home.section.seeDocument"> SEE MORE DOCUMENTS </Translate>
                  </h5>
                </div>
              </Col>
              <Col md="4">
                <h5 className="textAlignLeft">
                  <FontAwesomeIcon icon="home" className="orangeColor" />
                  <Translate contentKey="home.section.subsection2.title">Real Estate </Translate>
                </h5>
                <br></br>
                <div className="orangeColor">
                  <Translate contentKey="home.section.subsection2.text1">Lease Aggrement</Translate>
                </div>
                <div className="orangeColor">
                  <Translate contentKey="home.section.subsection2.text2">Eviction Notice</Translate>
                </div>
                <div className="orangeColor">
                  <Translate contentKey="home.section.subsection2.text3">Intent to Purchase Real Estate</Translate>
                </div>
                <div className="orangeColor">
                  <Translate contentKey="home.section.subsection2.text4">Quitclaim Deed </Translate>
                </div>
                <br></br>
                <br></br>
                <div className="orangeColor">
                  <h5>
                    <Translate contentKey="home.section.seeDocument"> SEE MORE DOCUMENTS </Translate>
                  </h5>
                </div>
              </Col>
              <Col md="4">
                <h5 className="textAlignLeft">
                  <FontAwesomeIcon icon="users" className="orangeColor" />
                  <Translate contentKey="home.section.subsection3.title">Family and persoal</Translate>
                </h5>
                <br></br>
                <div className="orangeColor">
                  <Translate contentKey="home.section.subsection3.text1">Last Will and Testament</Translate>
                </div>
                <div className="orangeColor">
                  <Translate contentKey="home.section.subsection3.text2">Living Will</Translate>
                </div>
                <div className="orangeColor">
                  <Translate contentKey="home.section.subsection3.text3">Divorce Settlement Aggrement</Translate>
                </div>
                <div className="orangeColor">
                  <Translate contentKey="home.section.subsection3.text4">Child Care Authorization</Translate>
                </div>
                <br></br>
                <br></br>
                <div className="orangeColor">
                  <h5>
                    <Translate contentKey="home.section.seeDocument"> SEE MORE DOCUMENTS </Translate>
                  </h5>
                </div>
              </Col>
              <Col md="12" className="padding1Percent">
                <Button type="submit" data-cy="submit" className="homeDocumentButton">
                  <b>
                    <Translate contentKey="home.section.seeAllDocument"> See all documents </Translate>
                  </b>
                </Button>
              </Col>
            </Row>
          </Col>
        </>
      )}
      {isOpenTIFFPage && <TryItForFree />}
    </Row>
  );
};

export default Home;
