import React, { useEffect } from 'react';
import { Link, useParams } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { openFile, byteSize, TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { getEntity } from './employee.reducer';

export const EmployeeDetail = () => {
  const dispatch = useAppDispatch();

  const { id } = useParams<'id'>();

  useEffect(() => {
    dispatch(getEntity(id));
  }, []);

  const employeeEntity = useAppSelector(state => state.employee.entity);
  return (
    <Row>
      <Col md="8">
        <h2 data-cy="employeeDetailsHeading">Translation missing for dispatchApplicationApp.employee.detail.title</h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="id">ID</span>
          </dt>
          <dd>{employeeEntity.id}</dd>
          <dt>
            <span id="fullName">Full Name</span>
          </dt>
          <dd>{employeeEntity.fullName}</dd>
          <dt>
            <span id="dob">Dob</span>
          </dt>
          <dd>{employeeEntity.dob ? <TextFormat value={employeeEntity.dob} type="date" format={APP_DATE_FORMAT} /> : null}</dd>
          <dt>
            <span id="gender">Gender</span>
          </dt>
          <dd>{employeeEntity.gender}</dd>
          <dt>
            <span id="mobile">Mobile</span>
          </dt>
          <dd>{employeeEntity.mobile}</dd>
          <dt>
            <span id="photo">Photo</span>
          </dt>
          <dd>
            {employeeEntity.photo ? (
              <div>
                {employeeEntity.photoContentType ? (
                  <a onClick={openFile(employeeEntity.photoContentType, employeeEntity.photo)}>
                    <img src={`data:${employeeEntity.photoContentType};base64,${employeeEntity.photo}`} style={{ maxHeight: '30px' }} />
                  </a>
                ) : null}
                <span>
                  {employeeEntity.photoContentType}, {byteSize(employeeEntity.photo)}
                </span>
              </div>
            ) : null}
          </dd>
          <dt>
            <span id="citizenshipNo">Citizenship No</span>
          </dt>
          <dd>{employeeEntity.citizenshipNo}</dd>
          <dt>
            <span id="panNo">Pan No</span>
          </dt>
          <dd>{employeeEntity.panNo}</dd>
          <dt>
            <span id="category">Category</span>
          </dt>
          <dd>{employeeEntity.category}</dd>
          <dt>
            <span id="detail">Detail</span>
          </dt>
          <dd>{employeeEntity.detail}</dd>
          <dt>
            <span id="status">Status</span>
          </dt>
          <dd>{employeeEntity.status}</dd>
          <dt>User</dt>
          <dd>{employeeEntity.user ? employeeEntity.user.id : ''}</dd>
        </dl>
        <Button tag={Link} to="/employee" replace color="info" data-cy="entityDetailsBackButton">
          <FontAwesomeIcon icon="arrow-left" /> <span className="d-none d-md-inline">Back</span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/employee/${employeeEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" /> <span className="d-none d-md-inline">Edit</span>
        </Button>
      </Col>
    </Row>
  );
};

export default EmployeeDetail;
