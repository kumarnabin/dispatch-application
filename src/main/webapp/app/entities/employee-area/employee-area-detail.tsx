import React, { useEffect } from 'react';
import { Link, useParams } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { getEntity } from './employee-area.reducer';

export const EmployeeAreaDetail = () => {
  const dispatch = useAppDispatch();

  const { id } = useParams<'id'>();

  useEffect(() => {
    dispatch(getEntity(id));
  }, []);

  const employeeAreaEntity = useAppSelector(state => state.employeeArea.entity);
  return (
    <Row>
      <Col md="8">
        <h2 data-cy="employeeAreaDetailsHeading">Employee Area</h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="id">ID</span>
          </dt>
          <dd>{employeeAreaEntity.id}</dd>
          <dt>
            <span id="status">Status</span>
          </dt>
          <dd>{employeeAreaEntity.status}</dd>
          <dt>
            <span id="publicationDate">Publication Date</span>
          </dt>
          <dd>
            {employeeAreaEntity.publicationDate ? (
              <TextFormat value={employeeAreaEntity.publicationDate} type="date" format={APP_DATE_FORMAT} />
            ) : null}
          </dd>
          <dt>Area</dt>
          <dd>{employeeAreaEntity.area ? employeeAreaEntity.area.id : ''}</dd>
          <dt>Employee</dt>
          <dd>{employeeAreaEntity.employee ? employeeAreaEntity.employee.id : ''}</dd>
        </dl>
        <Button tag={Link} to="/employee-area" replace color="info" data-cy="entityDetailsBackButton">
          <FontAwesomeIcon icon="arrow-left" /> <span className="d-none d-md-inline">Back</span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/employee-area/${employeeAreaEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" /> <span className="d-none d-md-inline">Edit</span>
        </Button>
      </Col>
    </Row>
  );
};

export default EmployeeAreaDetail;
