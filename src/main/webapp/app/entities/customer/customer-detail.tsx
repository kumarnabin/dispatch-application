import React, { useEffect } from 'react';
import { Link, useParams } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { TextFormat } from 'react-jhipster';
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
        <h2 data-cy="customerDetailsHeading">Customer</h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="id">ID</span>
          </dt>
          <dd>{customerEntity.id}</dd>
          <dt>
            <span id="voice">Voice</span>
          </dt>
          <dd>{customerEntity.voice}</dd>
          <dt>
            <span id="data">Data</span>
          </dt>
          <dd>{customerEntity.data}</dd>
          <dt>
            <span id="iptv">Iptv</span>
          </dt>
          <dd>{customerEntity.iptv}</dd>
          <dt>
            <span id="customerName">Customer Name</span>
          </dt>
          <dd>{customerEntity.customerName}</dd>
          <dt>
            <span id="contactNo">Contact No</span>
          </dt>
          <dd>{customerEntity.contactNo}</dd>
          <dt>
            <span id="oltPort">Olt Port</span>
          </dt>
          <dd>{customerEntity.oltPort}</dd>
          <dt>
            <span id="regDate">Reg Date</span>
          </dt>
          <dd>{customerEntity.regDate}</dd>
          <dt>
            <span id="fapPort">Fap Port</span>
          </dt>
          <dd>{customerEntity.fapPort}</dd>
          <dt>
            <span id="cpeSn">Cpe Sn</span>
          </dt>
          <dd>{customerEntity.cpeSn}</dd>
          <dt>
            <span id="cpeRx">Cpe Rx</span>
          </dt>
          <dd>{customerEntity.cpeRx}</dd>
          <dt>
            <span id="complain">Complain</span>
          </dt>
          <dd>{customerEntity.complain}</dd>
          <dt>
            <span id="remark">Remark</span>
          </dt>
          <dd>{customerEntity.remark}</dd>
          <dt>
            <span id="status">Status</span>
          </dt>
          <dd>{customerEntity.status}</dd>
          <dt>
            <span id="location">Location</span>
          </dt>
          <dd>{customerEntity.location}</dd>
          <dt>
            <span id="printDate">Print Date</span>
          </dt>
          <dd>
            {customerEntity.printDate ? <TextFormat value={customerEntity.printDate} type="date" format={APP_LOCAL_DATE_FORMAT} /> : null}
          </dd>
          <dt>
            <span id="publicationDate">Publication Date</span>
          </dt>
          <dd>
            {customerEntity.publicationDate ? (
              <TextFormat value={customerEntity.publicationDate} type="date" format={APP_DATE_FORMAT} />
            ) : null}
          </dd>
          <dt>Team</dt>
          <dd>{customerEntity.team ? customerEntity.team.id : ''}</dd>
        </dl>
        <Button tag={Link} to="/customer" replace color="info" data-cy="entityDetailsBackButton">
          <FontAwesomeIcon icon="arrow-left" /> <span className="d-none d-md-inline">Back</span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/customer/${customerEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" /> <span className="d-none d-md-inline">Edit</span>
        </Button>
      </Col>
    </Row>
  );
};

export default CustomerDetail;
