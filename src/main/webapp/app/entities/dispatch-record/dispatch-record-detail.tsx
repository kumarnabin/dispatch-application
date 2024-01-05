import React, { useEffect } from 'react';
import { Link, useParams } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { getEntity } from './dispatch-record.reducer';

export const DispatchRecordDetail = () => {
  const dispatch = useAppDispatch();

  const { id } = useParams<'id'>();

  useEffect(() => {
    dispatch(getEntity(id));
  }, []);

  const dispatchRecordEntity = useAppSelector(state => state.dispatchRecord.entity);
  return (
    <Row>
      <Col md="8">
        <h2 data-cy="dispatchRecordDetailsHeading">Dispatch Record</h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="id">ID</span>
          </dt>
          <dd>{dispatchRecordEntity.id}</dd>
          <dt>
            <span id="status">Status</span>
          </dt>
          <dd>{dispatchRecordEntity.status}</dd>
          <dt>
            <span id="publicationDate">Publication Date</span>
          </dt>
          <dd>
            {dispatchRecordEntity.publicationDate ? (
              <TextFormat value={dispatchRecordEntity.publicationDate} type="date" format={APP_DATE_FORMAT} />
            ) : null}
          </dd>
          <dt>Employee</dt>
          <dd>{dispatchRecordEntity.employee ? dispatchRecordEntity.employee.id : ''}</dd>
          <dt>Dispatch</dt>
          <dd>{dispatchRecordEntity.dispatch ? dispatchRecordEntity.dispatch.id : ''}</dd>
        </dl>
        <Button tag={Link} to="/dispatch-record" replace color="info" data-cy="entityDetailsBackButton">
          <FontAwesomeIcon icon="arrow-left" /> <span className="d-none d-md-inline">Back</span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/dispatch-record/${dispatchRecordEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" /> <span className="d-none d-md-inline">Edit</span>
        </Button>
      </Col>
    </Row>
  );
};

export default DispatchRecordDetail;
