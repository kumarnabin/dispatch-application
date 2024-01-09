import React, { useState, useEffect } from 'react';
import { Link, useNavigate, useParams } from 'react-router-dom';
import { Button, Row, Col, FormText } from 'reactstrap';
import { isNumber, Translate, translate, ValidatedField, ValidatedForm } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { convertDateTimeFromServer, convertDateTimeToServer, displayDefaultDateTime } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { IDispatch } from 'app/shared/model/dispatch.model';
import { getEntities as getDispatches } from 'app/entities/dispatch/dispatch.reducer';
import { IEmployee } from 'app/shared/model/employee.model';
import { getEntities as getEmployees } from 'app/entities/employee/employee.reducer';
import { IDispatchRecord } from 'app/shared/model/dispatch-record.model';
import { Status } from 'app/shared/model/enumerations/status.model';
import { getEntity, updateEntity, createEntity, reset } from './dispatch-record.reducer';

export const DispatchRecordUpdate = () => {
  const dispatch = useAppDispatch();

  const navigate = useNavigate();

  const { id } = useParams<'id'>();
  const isNew = id === undefined;

  const dispatches = useAppSelector(state => state.dispatch.entities);
  const employees = useAppSelector(state => state.employee.entities);
  const dispatchRecordEntity = useAppSelector(state => state.dispatchRecord.entity);
  const loading = useAppSelector(state => state.dispatchRecord.loading);
  const updating = useAppSelector(state => state.dispatchRecord.updating);
  const updateSuccess = useAppSelector(state => state.dispatchRecord.updateSuccess);
  const statusValues = Object.keys(Status);

  const handleClose = () => {
    navigate('/dispatch-record' + location.search);
  };

  useEffect(() => {
    if (isNew) {
      dispatch(reset());
    } else {
      dispatch(getEntity(id));
    }

    dispatch(getDispatches({}));
    dispatch(getEmployees({}));
  }, []);

  useEffect(() => {
    if (updateSuccess) {
      handleClose();
    }
  }, [updateSuccess]);

  // eslint-disable-next-line complexity
  const saveEntity = values => {
    if (values.id !== undefined && typeof values.id !== 'number') {
      values.id = Number(values.id);
    }
    values.publicationDate = convertDateTimeToServer(values.publicationDate);

    const entity = {
      ...dispatchRecordEntity,
      ...values,
      dispatch: dispatches.find(it => it.id.toString() === values.dispatch.toString()),
      employee: employees.find(it => it.id.toString() === values.employee.toString()),
    };

    if (isNew) {
      dispatch(createEntity(entity));
    } else {
      dispatch(updateEntity(entity));
    }
  };

  const defaultValues = () =>
    isNew
      ? {
          publicationDate: displayDefaultDateTime(),
        }
      : {
          status: 'OPEN',
          ...dispatchRecordEntity,
          publicationDate: convertDateTimeFromServer(dispatchRecordEntity.publicationDate),
          dispatch: dispatchRecordEntity?.dispatch?.id,
          employee: dispatchRecordEntity?.employee?.id,
        };

  return (
    <div>
      <Row className="justify-content-center">
        <Col md="8">
          <h2 id="dispatchApplicationApp.dispatchRecord.home.createOrEditLabel" data-cy="DispatchRecordCreateUpdateHeading">
            <Translate contentKey="dispatchApplicationApp.dispatchRecord.home.createOrEditLabel">Create or edit a DispatchRecord</Translate>
          </h2>
        </Col>
      </Row>
      <Row className="justify-content-center">
        <Col md="8">
          {loading ? (
            <p>Loading...</p>
          ) : (
            <ValidatedForm defaultValues={defaultValues()} onSubmit={saveEntity}>
              {!isNew ? (
                <ValidatedField
                  name="id"
                  required
                  readOnly
                  id="dispatch-record-id"
                  label={translate('global.field.id')}
                  validate={{ required: true }}
                />
              ) : null}
              <ValidatedField
                label={translate('dispatchApplicationApp.dispatchRecord.remark')}
                id="dispatch-record-remark"
                name="remark"
                data-cy="remark"
                type="text"
              />
              <ValidatedField
                label={translate('dispatchApplicationApp.dispatchRecord.status')}
                id="dispatch-record-status"
                name="status"
                data-cy="status"
                type="select"
              >
                {statusValues.map(status => (
                  <option value={status} key={status}>
                    {translate('dispatchApplicationApp.Status.' + status)}
                  </option>
                ))}
              </ValidatedField>
              <ValidatedField
                label={translate('dispatchApplicationApp.dispatchRecord.publicationDate')}
                id="dispatch-record-publicationDate"
                name="publicationDate"
                data-cy="publicationDate"
                type="datetime-local"
                placeholder="YYYY-MM-DD HH:mm"
              />
              <ValidatedField
                id="dispatch-record-dispatch"
                name="dispatch"
                data-cy="dispatch"
                label={translate('dispatchApplicationApp.dispatchRecord.dispatch')}
                type="select"
              >
                <option value="" key="0" />
                {dispatches
                  ? dispatches.map(otherEntity => (
                      <option value={otherEntity.id} key={otherEntity.id}>
                        {otherEntity.id}
                      </option>
                    ))
                  : null}
              </ValidatedField>
              <ValidatedField
                id="dispatch-record-employee"
                name="employee"
                data-cy="employee"
                label={translate('dispatchApplicationApp.dispatchRecord.employee')}
                type="select"
              >
                <option value="" key="0" />
                {employees
                  ? employees.map(otherEntity => (
                      <option value={otherEntity.id} key={otherEntity.id}>
                        {otherEntity.id}
                      </option>
                    ))
                  : null}
              </ValidatedField>
              <Button tag={Link} id="cancel-save" data-cy="entityCreateCancelButton" to="/dispatch-record" replace color="info">
                <FontAwesomeIcon icon="arrow-left" />
                &nbsp;
                <span className="d-none d-md-inline">
                  <Translate contentKey="entity.action.back">Back</Translate>
                </span>
              </Button>
              &nbsp;
              <Button color="primary" id="save-entity" data-cy="entityCreateSaveButton" type="submit" disabled={updating}>
                <FontAwesomeIcon icon="save" />
                &nbsp;
                <Translate contentKey="entity.action.save">Save</Translate>
              </Button>
            </ValidatedForm>
          )}
        </Col>
      </Row>
    </div>
  );
};

export default DispatchRecordUpdate;
