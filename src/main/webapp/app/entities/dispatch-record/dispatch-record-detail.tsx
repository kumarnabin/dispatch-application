import React, { useEffect } from 'react';
import { Link, useParams } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { Translate, TextFormat } from 'react-jhipster';
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
        <h2 data-cy="dispatchRecordDetailsHeading">
          <Translate contentKey="dispatchApplicationApp.dispatchRecord.detail.title">DispatchRecord</Translate>
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="id">
              <Translate contentKey="global.field.id">ID</Translate>
            </span>
          </dt>
          <dd>{dispatchRecordEntity.id}</dd>
          <dt>
            <span id="remark">
              <Translate contentKey="dispatchApplicationApp.dispatchRecord.remark">Remark</Translate>
            </span>
          </dt>
          <dd>{dispatchRecordEntity.remark}</dd>
          <dt>
            <span id="status">
              <Translate contentKey="dispatchApplicationApp.dispatchRecord.status">Status</Translate>
            </span>
          </dt>
          <dd>{dispatchRecordEntity.status}</dd>
          <dt>
            <span id="publicationDate">
              <Translate contentKey="dispatchApplicationApp.dispatchRecord.publicationDate">Publication Date</Translate>
            </span>
          </dt>
          <dd>
            {dispatchRecordEntity.publicationDate ? (
              <TextFormat value={dispatchRecordEntity.publicationDate} type="date" format={APP_DATE_FORMAT} />
            ) : null}
          </dd>
          <dt>
            <Translate contentKey="dispatchApplicationApp.dispatchRecord.dispatch">Dispatch</Translate>
          </dt>
          <dd>{dispatchRecordEntity.dispatch ? dispatchRecordEntity.dispatch.id : ''}</dd>
          <dt>
            <Translate contentKey="dispatchApplicationApp.dispatchRecord.employee">Employee</Translate>
          </dt>
          <dd>{dispatchRecordEntity.employee ? dispatchRecordEntity.employee.id : ''}</dd>
        </dl>
        <Button tag={Link} to="/dispatch-record" replace color="info" data-cy="entityDetailsBackButton">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/dispatch-record/${dispatchRecordEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

export default DispatchRecordDetail;
