import React, { useEffect } from 'react';
import { Link, useParams } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { Translate, TextFormat } from 'react-jhipster';
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
        <h2 data-cy="employeeAreaDetailsHeading">
          <Translate contentKey="dispatchApplicationApp.employeeArea.detail.title">EmployeeArea</Translate>
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="id">
              <Translate contentKey="global.field.id">ID</Translate>
            </span>
          </dt>
          <dd>{employeeAreaEntity.id}</dd>
          <dt>
            <span id="status">
              <Translate contentKey="dispatchApplicationApp.employeeArea.status">Status</Translate>
            </span>
          </dt>
          <dd>{employeeAreaEntity.status}</dd>
          <dt>
            <span id="publicationDate">
              <Translate contentKey="dispatchApplicationApp.employeeArea.publicationDate">Publication Date</Translate>
            </span>
          </dt>
          <dd>
            {employeeAreaEntity.publicationDate ? (
              <TextFormat value={employeeAreaEntity.publicationDate} type="date" format={APP_DATE_FORMAT} />
            ) : null}
          </dd>
          <dt>
            <Translate contentKey="dispatchApplicationApp.employeeArea.area">Area</Translate>
          </dt>
          <dd>{employeeAreaEntity.area ? employeeAreaEntity.area.id : ''}</dd>
          <dt>
            <Translate contentKey="dispatchApplicationApp.employeeArea.employee">Employee</Translate>
          </dt>
          <dd>{employeeAreaEntity.employee ? employeeAreaEntity.employee.id : ''}</dd>
        </dl>
        <Button tag={Link} to="/employee-area" replace color="info" data-cy="entityDetailsBackButton">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/employee-area/${employeeAreaEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

export default EmployeeAreaDetail;
