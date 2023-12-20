import React, { useState, useEffect } from 'react';
import { Link, useNavigate, useParams } from 'react-router-dom';
import { Button, Row, Col, FormText } from 'reactstrap';
import { isNumber, ValidatedField, ValidatedForm } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { convertDateTimeFromServer, convertDateTimeToServer, displayDefaultDateTime } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { IMasterCircuit } from 'app/shared/model/master-circuit.model';
import { getEntities as getMasterCircuits } from 'app/entities/master-circuit/master-circuit.reducer';
import { IServiceProvider } from 'app/shared/model/service-provider.model';
import { getEntity, updateEntity, createEntity, reset } from './service-provider.reducer';

export const ServiceProviderUpdate = () => {
  const dispatch = useAppDispatch();

  const navigate = useNavigate();

  const { id } = useParams<'id'>();
  const isNew = id === undefined;

  const masterCircuits = useAppSelector(state => state.masterCircuit.entities);
  const serviceProviderEntity = useAppSelector(state => state.serviceProvider.entity);
  const loading = useAppSelector(state => state.serviceProvider.loading);
  const updating = useAppSelector(state => state.serviceProvider.updating);
  const updateSuccess = useAppSelector(state => state.serviceProvider.updateSuccess);

  const handleClose = () => {
    navigate('/service-provider');
  };

  useEffect(() => {
    if (isNew) {
      dispatch(reset());
    } else {
      dispatch(getEntity(id));
    }

    dispatch(getMasterCircuits({}));
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

    const entity = {
      ...serviceProviderEntity,
      ...values,
    };

    if (isNew) {
      dispatch(createEntity(entity));
    } else {
      dispatch(updateEntity(entity));
    }
  };

  const defaultValues = () =>
    isNew
      ? {}
      : {
          ...serviceProviderEntity,
        };

  return (
    <div>
      <Row className="justify-content-center">
        <Col md="8">
          <h2 id="dispatchApplicationApp.serviceProvider.home.createOrEditLabel" data-cy="ServiceProviderCreateUpdateHeading">
            Create or edit a Service Provider
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
                <ValidatedField name="id" required readOnly id="service-provider-id" label="ID" validate={{ required: true }} />
              ) : null}
              <ValidatedField label="Name" id="service-provider-name" name="name" data-cy="name" type="text" />
              <ValidatedField label="Address" id="service-provider-address" name="address" data-cy="address" type="text" />
              <Button tag={Link} id="cancel-save" data-cy="entityCreateCancelButton" to="/service-provider" replace color="info">
                <FontAwesomeIcon icon="arrow-left" />
                &nbsp;
                <span className="d-none d-md-inline">Back</span>
              </Button>
              &nbsp;
              <Button color="primary" id="save-entity" data-cy="entityCreateSaveButton" type="submit" disabled={updating}>
                <FontAwesomeIcon icon="save" />
                &nbsp; Save
              </Button>
            </ValidatedForm>
          )}
        </Col>
      </Row>
    </div>
  );
};

export default ServiceProviderUpdate;
