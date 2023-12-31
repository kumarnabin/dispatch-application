import React, { useEffect } from 'react';
import { Link, useParams } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { TextFormat } from 'react-jhipster';
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
        <h2 data-cy="dispatchDetailsHeading">Dispatch</h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="id">ID</span>
          </dt>
          <dd>{dispatchEntity.id}</dd>
          <dt>
            <span id="voice">Voice</span>
          </dt>
          <dd>{dispatchEntity.voice}</dd>
          <dt>
            <span id="data">Data</span>
          </dt>
          <dd>{dispatchEntity.data}</dd>
          <dt>
            <span id="iptv">Iptv</span>
          </dt>
          <dd>{dispatchEntity.iptv}</dd>
          <dt>
            <span id="customerName">Customer Name</span>
          </dt>
          <dd>{dispatchEntity.customerName}</dd>
          <dt>
            <span id="contactNo">Contact No</span>
          </dt>
          <dd>{dispatchEntity.contactNo}</dd>
          <dt>
            <span id="oltPort">Olt Port</span>
          </dt>
          <dd>{dispatchEntity.oltPort}</dd>
          <dt>
            <span id="regDate">Reg Date</span>
          </dt>
          <dd>{dispatchEntity.regDate}</dd>
          <dt>
            <span id="fapPort">Fap Port</span>
          </dt>
          <dd>{dispatchEntity.fapPort}</dd>
          <dt>
            <span id="cpeSn">Cpe Sn</span>
          </dt>
          <dd>{dispatchEntity.cpeSn}</dd>
          <dt>
            <span id="cpeRx">Cpe Rx</span>
          </dt>
          <dd>{dispatchEntity.cpeRx}</dd>
          <dt>
            <span id="complain">Complain</span>
          </dt>
          <dd>{dispatchEntity.complain}</dd>
          <dt>
            <span id="remark">Remark</span>
          </dt>
          <dd>{dispatchEntity.remark}</dd>
          <dt>
            <span id="status">Status</span>
          </dt>
          <dd>{dispatchEntity.status}</dd>
          <dt>
            <span id="location">Location</span>
          </dt>
          <dd>{dispatchEntity.location}</dd>
          <dt>
            <span id="printDate">Print Date</span>
          </dt>
          <dd>
            {dispatchEntity.printDate ? <TextFormat value={dispatchEntity.printDate} type="date" format={APP_LOCAL_DATE_FORMAT} /> : null}
          </dd>
          <dt>
            <span id="publicationDate">Publication Date</span>
          </dt>
          <dd>
            {dispatchEntity.publicationDate ? (
              <TextFormat value={dispatchEntity.publicationDate} type="date" format={APP_DATE_FORMAT} />
            ) : null}
          </dd>
          <dt>Team</dt>
          <dd>{dispatchEntity.team ? dispatchEntity.team.id : ''}</dd>
        </dl>
        <Button tag={Link} to="/dispatch" replace color="info" data-cy="entityDetailsBackButton">
          <FontAwesomeIcon icon="arrow-left" /> <span className="d-none d-md-inline">Back</span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/dispatch/${dispatchEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" /> <span className="d-none d-md-inline">Edit</span>
        </Button>
      </Col>
    </Row>
  );
};

export default DispatchDetail;
