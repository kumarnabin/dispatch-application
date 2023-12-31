import React, { useEffect } from 'react';
import { Link, useParams } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { TextFormat } from 'react-jhipster';
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
        <h2 data-cy="attendanceDetailsHeading">Attendance</h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="id">ID</span>
          </dt>
          <dd>{attendanceEntity.id}</dd>
          <dt>
            <span id="status">Status</span>
          </dt>
          <dd>{attendanceEntity.status}</dd>
          <dt>
            <span id="meterPics">Meter Pics</span>
          </dt>
          <dd>{attendanceEntity.meterPics}</dd>
          <dt>
            <span id="publicationDate">Publication Date</span>
          </dt>
          <dd>
            {attendanceEntity.publicationDate ? (
              <TextFormat value={attendanceEntity.publicationDate} type="date" format={APP_DATE_FORMAT} />
            ) : null}
          </dd>
          <dt>Employee</dt>
          <dd>{attendanceEntity.employee ? attendanceEntity.employee.id : ''}</dd>
        </dl>
        <Button tag={Link} to="/attendance" replace color="info" data-cy="entityDetailsBackButton">
          <FontAwesomeIcon icon="arrow-left" /> <span className="d-none d-md-inline">Back</span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/attendance/${attendanceEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" /> <span className="d-none d-md-inline">Edit</span>
        </Button>
      </Col>
    </Row>
  );
};

export default AttendanceDetail;
