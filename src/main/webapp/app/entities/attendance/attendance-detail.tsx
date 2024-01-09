import React, { useEffect } from 'react';
import { Link, useParams } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { Translate, TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { getEntity } from './attendance.reducer';

export const AttendanceDetail = () => {
  const dispatch = useAppDispatch();

  const { id } = useParams<'id'>();

  useEffect(() => {
    dispatch(getEntity(id));
  }, []);

  const attendanceEntity = useAppSelector(state => state.attendance.entity);
  return (
    <Row>
      <Col md="8">
        <h2 data-cy="attendanceDetailsHeading">
          <Translate contentKey="dispatchApplicationApp.attendance.detail.title">Attendance</Translate>
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="id">
              <Translate contentKey="global.field.id">ID</Translate>
            </span>
          </dt>
          <dd>{attendanceEntity.id}</dd>
          <dt>
            <span id="status">
              <Translate contentKey="dispatchApplicationApp.attendance.status">Status</Translate>
            </span>
          </dt>
          <dd>{attendanceEntity.status}</dd>
          <dt>
            <span id="meterPics">
              <Translate contentKey="dispatchApplicationApp.attendance.meterPics">Meter Pics</Translate>
            </span>
          </dt>
          <dd>{attendanceEntity.meterPics}</dd>
          <dt>
            <span id="publicationDate">
              <Translate contentKey="dispatchApplicationApp.attendance.publicationDate">Publication Date</Translate>
            </span>
          </dt>
          <dd>
            {attendanceEntity.publicationDate ? (
              <TextFormat value={attendanceEntity.publicationDate} type="date" format={APP_DATE_FORMAT} />
            ) : null}
          </dd>
          <dt>
            <Translate contentKey="dispatchApplicationApp.attendance.employee">Employee</Translate>
          </dt>
          <dd>{attendanceEntity.employee ? attendanceEntity.employee.id : ''}</dd>
        </dl>
        <Button tag={Link} to="/attendance" replace color="info" data-cy="entityDetailsBackButton">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/attendance/${attendanceEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

export default AttendanceDetail;
