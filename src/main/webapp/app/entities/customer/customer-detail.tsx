import React, { useEffect } from 'react';
import { Link, useParams } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { Translate, TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { getEntity } from './customer.reducer';

export const CustomerDetail = () => {
  const dispatch = useAppDispatch();

  const { id } = useParams<'id'>();

  useEffect(() => {
    dispatch(getEntity(id));
  }, []);

  const customerEntity = useAppSelector(state => state.customer.entity);
  return (
    <Row>
      <Col md="8">
        <h2 data-cy="customerDetailsHeading">
          <Translate contentKey="dispatchApplicationApp.customer.detail.title">Customer</Translate>
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="id">
              <Translate contentKey="global.field.id">ID</Translate>
            </span>
          </dt>
          <dd>{customerEntity.id}</dd>
          <dt>
            <span id="voice">
              <Translate contentKey="dispatchApplicationApp.customer.voice">Voice</Translate>
            </span>
          </dt>
          <dd>{customerEntity.voice}</dd>
          <dt>
            <span id="data">
              <Translate contentKey="dispatchApplicationApp.customer.data">Data</Translate>
            </span>
          </dt>
          <dd>{customerEntity.data}</dd>
          <dt>
            <span id="iptv">
              <Translate contentKey="dispatchApplicationApp.customer.iptv">Iptv</Translate>
            </span>
          </dt>
          <dd>{customerEntity.iptv}</dd>
          <dt>
            <span id="customerName">
              <Translate contentKey="dispatchApplicationApp.customer.customerName">Customer Name</Translate>
            </span>
          </dt>
          <dd>{customerEntity.customerName}</dd>
          <dt>
            <span id="contactNo">
              <Translate contentKey="dispatchApplicationApp.customer.contactNo">Contact No</Translate>
            </span>
          </dt>
          <dd>{customerEntity.contactNo}</dd>
          <dt>
            <span id="oltPort">
              <Translate contentKey="dispatchApplicationApp.customer.oltPort">Olt Port</Translate>
            </span>
          </dt>
          <dd>{customerEntity.oltPort}</dd>
          <dt>
            <span id="regDate">
              <Translate contentKey="dispatchApplicationApp.customer.regDate">Reg Date</Translate>
            </span>
          </dt>
          <dd>{customerEntity.regDate}</dd>
          <dt>
            <span id="fapPort">
              <Translate contentKey="dispatchApplicationApp.customer.fapPort">Fap Port</Translate>
            </span>
          </dt>
          <dd>{customerEntity.fapPort}</dd>
          <dt>
            <span id="cpeSn">
              <Translate contentKey="dispatchApplicationApp.customer.cpeSn">Cpe Sn</Translate>
            </span>
          </dt>
          <dd>{customerEntity.cpeSn}</dd>
          <dt>
            <span id="cpeRx">
              <Translate contentKey="dispatchApplicationApp.customer.cpeRx">Cpe Rx</Translate>
            </span>
          </dt>
          <dd>{customerEntity.cpeRx}</dd>
          <dt>
            <span id="complain">
              <Translate contentKey="dispatchApplicationApp.customer.complain">Complain</Translate>
            </span>
          </dt>
          <dd>{customerEntity.complain}</dd>
          <dt>
            <span id="remark">
              <Translate contentKey="dispatchApplicationApp.customer.remark">Remark</Translate>
            </span>
          </dt>
          <dd>{customerEntity.remark}</dd>
          <dt>
            <span id="status">
              <Translate contentKey="dispatchApplicationApp.customer.status">Status</Translate>
            </span>
          </dt>
          <dd>{customerEntity.status}</dd>
          <dt>
            <span id="location">
              <Translate contentKey="dispatchApplicationApp.customer.location">Location</Translate>
            </span>
          </dt>
          <dd>{customerEntity.location}</dd>
          <dt>
            <span id="printDate">
              <Translate contentKey="dispatchApplicationApp.customer.printDate">Print Date</Translate>
            </span>
          </dt>
          <dd>
            {customerEntity.printDate ? <TextFormat value={customerEntity.printDate} type="date" format={APP_LOCAL_DATE_FORMAT} /> : null}
          </dd>
          <dt>
            <span id="publicationDate">
              <Translate contentKey="dispatchApplicationApp.customer.publicationDate">Publication Date</Translate>
            </span>
          </dt>
          <dd>
            {customerEntity.publicationDate ? (
              <TextFormat value={customerEntity.publicationDate} type="date" format={APP_DATE_FORMAT} />
            ) : null}
          </dd>
          <dt>
            <Translate contentKey="dispatchApplicationApp.customer.team">Team</Translate>
          </dt>
          <dd>{customerEntity.team ? customerEntity.team.id : ''}</dd>
        </dl>
        <Button tag={Link} to="/customer" replace color="info" data-cy="entityDetailsBackButton">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/customer/${customerEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

export default CustomerDetail;
