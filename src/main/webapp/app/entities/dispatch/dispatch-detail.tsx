import React, { useEffect } from 'react';
import { Link, useParams } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { Translate, TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { getEntity } from './dispatch.reducer';

export const DispatchDetail = () => {
  const dispatch = useAppDispatch();

  const { id } = useParams<'id'>();

  useEffect(() => {
    dispatch(getEntity(id));
  }, []);

  const dispatchEntity = useAppSelector(state => state.dispatch.entity);
  return (
    <Row>
      <Col md="8">
        <h2 data-cy="dispatchDetailsHeading">
          <Translate contentKey="dispatchApplicationApp.dispatch.detail.title">Dispatch</Translate>
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="id">
              <Translate contentKey="global.field.id">ID</Translate>
            </span>
          </dt>
          <dd>{dispatchEntity.id}</dd>
          <dt>
            <span id="voice">
              <Translate contentKey="dispatchApplicationApp.dispatch.voice">Voice</Translate>
            </span>
          </dt>
          <dd>{dispatchEntity.voice}</dd>
          <dt>
            <span id="data">
              <Translate contentKey="dispatchApplicationApp.dispatch.data">Data</Translate>
            </span>
          </dt>
          <dd>{dispatchEntity.data}</dd>
          <dt>
            <span id="iptv">
              <Translate contentKey="dispatchApplicationApp.dispatch.iptv">Iptv</Translate>
            </span>
          </dt>
          <dd>{dispatchEntity.iptv}</dd>
          <dt>
            <span id="customerName">
              <Translate contentKey="dispatchApplicationApp.dispatch.customerName">Customer Name</Translate>
            </span>
          </dt>
          <dd>{dispatchEntity.customerName}</dd>
          <dt>
            <span id="contactNo">
              <Translate contentKey="dispatchApplicationApp.dispatch.contactNo">Contact No</Translate>
            </span>
          </dt>
          <dd>{dispatchEntity.contactNo}</dd>
          <dt>
            <span id="oltPort">
              <Translate contentKey="dispatchApplicationApp.dispatch.oltPort">Olt Port</Translate>
            </span>
          </dt>
          <dd>{dispatchEntity.oltPort}</dd>
          <dt>
            <span id="regDate">
              <Translate contentKey="dispatchApplicationApp.dispatch.regDate">Reg Date</Translate>
            </span>
          </dt>
          <dd>{dispatchEntity.regDate}</dd>
          <dt>
            <span id="fapPort">
              <Translate contentKey="dispatchApplicationApp.dispatch.fapPort">Fap Port</Translate>
            </span>
          </dt>
          <dd>{dispatchEntity.fapPort}</dd>
          <dt>
            <span id="cpeSn">
              <Translate contentKey="dispatchApplicationApp.dispatch.cpeSn">Cpe Sn</Translate>
            </span>
          </dt>
          <dd>{dispatchEntity.cpeSn}</dd>
          <dt>
            <span id="cpeRx">
              <Translate contentKey="dispatchApplicationApp.dispatch.cpeRx">Cpe Rx</Translate>
            </span>
          </dt>
          <dd>{dispatchEntity.cpeRx}</dd>
          <dt>
            <span id="complain">
              <Translate contentKey="dispatchApplicationApp.dispatch.complain">Complain</Translate>
            </span>
          </dt>
          <dd>{dispatchEntity.complain}</dd>
          <dt>
            <span id="remark">
              <Translate contentKey="dispatchApplicationApp.dispatch.remark">Remark</Translate>
            </span>
          </dt>
          <dd>{dispatchEntity.remark}</dd>
          <dt>
            <span id="status">
              <Translate contentKey="dispatchApplicationApp.dispatch.status">Status</Translate>
            </span>
          </dt>
          <dd>{dispatchEntity.status}</dd>
          <dt>
            <span id="location">
              <Translate contentKey="dispatchApplicationApp.dispatch.location">Location</Translate>
            </span>
          </dt>
          <dd>{dispatchEntity.location}</dd>
          <dt>
            <span id="printDate">
              <Translate contentKey="dispatchApplicationApp.dispatch.printDate">Print Date</Translate>
            </span>
          </dt>
          <dd>
            {dispatchEntity.printDate ? <TextFormat value={dispatchEntity.printDate} type="date" format={APP_LOCAL_DATE_FORMAT} /> : null}
          </dd>
          <dt>
            <span id="publicationDate">
              <Translate contentKey="dispatchApplicationApp.dispatch.publicationDate">Publication Date</Translate>
            </span>
          </dt>
          <dd>
            {dispatchEntity.publicationDate ? (
              <TextFormat value={dispatchEntity.publicationDate} type="date" format={APP_DATE_FORMAT} />
            ) : null}
          </dd>
          <dt>
            <Translate contentKey="dispatchApplicationApp.dispatch.team">Team</Translate>
          </dt>
          <dd>{dispatchEntity.team ? dispatchEntity.team.id : ''}</dd>
        </dl>
        <Button tag={Link} to="/dispatch" replace color="info" data-cy="entityDetailsBackButton">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/dispatch/${dispatchEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

export default DispatchDetail;
