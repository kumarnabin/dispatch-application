import React, { useEffect } from 'react';
import { Link, useParams } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { Translate, openFile, byteSize, TextFormat } from 'react-jhipster';
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
        <h2 data-cy="employeeDetailsHeading">
          <Translate contentKey="dispatchApplicationApp.employee.detail.title">Employee</Translate>
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="id">
              <Translate contentKey="global.field.id">ID</Translate>
            </span>
          </dt>
          <dd>{employeeEntity.id}</dd>
          <dt>
            <span id="fullName">
              <Translate contentKey="dispatchApplicationApp.employee.fullName">Full Name</Translate>
            </span>
          </dt>
          <dd>{employeeEntity.fullName}</dd>
          <dt>
            <span id="dob">
              <Translate contentKey="dispatchApplicationApp.employee.dob">Dob</Translate>
            </span>
          </dt>
          <dd>{employeeEntity.dob ? <TextFormat value={employeeEntity.dob} type="date" format={APP_DATE_FORMAT} /> : null}</dd>
          <dt>
            <span id="gender">
              <Translate contentKey="dispatchApplicationApp.employee.gender">Gender</Translate>
            </span>
          </dt>
          <dd>{employeeEntity.gender}</dd>
          <dt>
            <span id="mobile">
              <Translate contentKey="dispatchApplicationApp.employee.mobile">Mobile</Translate>
            </span>
          </dt>
          <dd>{employeeEntity.mobile}</dd>
          <dt>
            <span id="photo">
              <Translate contentKey="dispatchApplicationApp.employee.photo">Photo</Translate>
            </span>
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
            <span id="citizenshipNo">
              <Translate contentKey="dispatchApplicationApp.employee.citizenshipNo">Citizenship No</Translate>
            </span>
          </dt>
          <dd>{employeeEntity.citizenshipNo}</dd>
          <dt>
            <span id="panNo">
              <Translate contentKey="dispatchApplicationApp.employee.panNo">Pan No</Translate>
            </span>
          </dt>
          <dd>{employeeEntity.panNo}</dd>
          <dt>
            <span id="category">
              <Translate contentKey="dispatchApplicationApp.employee.category">Category</Translate>
            </span>
          </dt>
          <dd>{employeeEntity.category}</dd>
          <dt>
            <span id="detail">
              <Translate contentKey="dispatchApplicationApp.employee.detail">Detail</Translate>
            </span>
          </dt>
          <dd>{employeeEntity.detail}</dd>
          <dt>
            <span id="status">
              <Translate contentKey="dispatchApplicationApp.employee.status">Status</Translate>
            </span>
          </dt>
          <dd>{employeeEntity.status}</dd>
          <dt>
            <Translate contentKey="dispatchApplicationApp.employee.user">User</Translate>
          </dt>
          <dd>{employeeEntity.user ? employeeEntity.user.id : ''}</dd>
        </dl>
        <Button tag={Link} to="/employee" replace color="info" data-cy="entityDetailsBackButton">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/employee/${employeeEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

export default EmployeeDetail;
