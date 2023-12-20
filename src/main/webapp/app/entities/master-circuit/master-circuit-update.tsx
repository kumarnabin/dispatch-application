import React, { useState, useEffect } from 'react';
import { Link, useNavigate, useParams } from 'react-router-dom';
import { Button, Row, Col, FormText } from 'reactstrap';
import { isNumber, ValidatedField, ValidatedForm } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { convertDateTimeFromServer, convertDateTimeToServer, displayDefaultDateTime } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { IServiceProvider } from 'app/shared/model/service-provider.model';
import { getEntities as getServiceProviders } from 'app/entities/service-provider/service-provider.reducer';
import { IMasterCircuit } from 'app/shared/model/master-circuit.model';
import { getEntity, updateEntity, createEntity, reset } from './master-circuit.reducer';

export const MasterCircuitUpdate = () => {
  const dispatch = useAppDispatch();

  const navigate = useNavigate();

  const { id } = useParams<'id'>();
  const isNew = id === undefined;

  const serviceProviders = useAppSelector(state => state.serviceProvider.entities);
  const masterCircuitEntity = useAppSelector(state => state.masterCircuit.entity);
  const loading = useAppSelector(state => state.masterCircuit.loading);
  const updating = useAppSelector(state => state.masterCircuit.updating);
  const updateSuccess = useAppSelector(state => state.masterCircuit.updateSuccess);

  const handleClose = () => {
    navigate('/master-circuit');
  };

  useEffect(() => {
    if (isNew) {
      dispatch(reset());
    } else {
      dispatch(getEntity(id));
    }

    dispatch(getServiceProviders({}));
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
      ...masterCircuitEntity,
      ...values,
      serviceProviders: mapIdList(values.serviceProviders),
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
          ...masterCircuitEntity,
          serviceProviders: masterCircuitEntity?.serviceProviders?.map(e => e.id.toString()),
        };

  return (
    <div>
      <Row className="justify-content-center">
        <Col md="8">
          <h2 id="dispatchApplicationApp.masterCircuit.home.createOrEditLabel" data-cy="MasterCircuitCreateUpdateHeading">
            Create or edit a Master Circuit
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
                <ValidatedField name="id" required readOnly id="master-circuit-id" label="ID" validate={{ required: true }} />
              ) : null}
              <ValidatedField label="Name" id="master-circuit-name" name="name" data-cy="name" type="text" />
              <ValidatedField label="Address" id="master-circuit-address" name="address" data-cy="address" type="text" />
              <ValidatedField
                label="Service Provider"
                id="master-circuit-serviceProvider"
                data-cy="serviceProvider"
                type="select"
                multiple
                name="serviceProviders"
              >
                <option value="" key="0" />
                {serviceProviders
                  ? serviceProviders.map(otherEntity => (
                      <option value={otherEntity.id} key={otherEntity.id}>
                        {otherEntity.id}
                      </option>
                    ))
                  : null}
              </ValidatedField>
              <Button tag={Link} id="cancel-save" data-cy="entityCreateCancelButton" to="/master-circuit" replace color="info">
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

export default MasterCircuitUpdate;
