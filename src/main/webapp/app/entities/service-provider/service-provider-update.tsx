import React, { useState, useEffect } from 'react';
import { Link, useNavigate, useParams } from 'react-router-dom';
import { Button, Row, Col, FormText } from 'reactstrap';
import { isNumber, Translate, translate, ValidatedField, ValidatedForm } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { convertDateTimeFromServer, convertDateTimeToServer, displayDefaultDateTime } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { IServiceProvider } from 'app/shared/model/service-provider.model';
import { Status } from 'app/shared/model/enumerations/status.model';
import { getEntity, updateEntity, createEntity, reset } from './service-provider.reducer';

export const ServiceProviderUpdate = () => {
  const dispatch = useAppDispatch();

  const navigate = useNavigate();

  const { id } = useParams<'id'>();
  const isNew = id === undefined;

  const serviceProviderEntity = useAppSelector(state => state.serviceProvider.entity);
  const loading = useAppSelector(state => state.serviceProvider.loading);
  const updating = useAppSelector(state => state.serviceProvider.updating);
  const updateSuccess = useAppSelector(state => state.serviceProvider.updateSuccess);
  const statusValues = Object.keys(Status);

  const handleClose = () => {
    navigate('/service-provider' + location.search);
  };

  useEffect(() => {
    if (isNew) {
      dispatch(reset());
    } else {
      dispatch(getEntity(id));
    }
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
          status: 'OPEN',
          ...serviceProviderEntity,
        };

  return (
    <div>
      <Row className="justify-content-center">
        <Col md="8">
          <h2 id="dispatchApplicationApp.serviceProvider.home.createOrEditLabel" data-cy="ServiceProviderCreateUpdateHeading">
            <Translate contentKey="dispatchApplicationApp.serviceProvider.home.createOrEditLabel">
              Create or edit a ServiceProvider
            </Translate>
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
                  id="service-provider-id"
                  label={translate('global.field.id')}
                  validate={{ required: true }}
                />
              ) : null}
              <ValidatedField
                label={translate('dispatchApplicationApp.serviceProvider.name')}
                id="service-provider-name"
                name="name"
                data-cy="name"
                type="text"
              />
              <ValidatedField
                label={translate('dispatchApplicationApp.serviceProvider.code')}
                id="service-provider-code"
                name="code"
                data-cy="code"
                type="text"
              />
              <ValidatedField
                label={translate('dispatchApplicationApp.serviceProvider.phone')}
                id="service-provider-phone"
                name="phone"
                data-cy="phone"
                type="text"
              />
              <ValidatedField
                label={translate('dispatchApplicationApp.serviceProvider.address')}
                id="service-provider-address"
                name="address"
                data-cy="address"
                type="text"
              />
              <ValidatedField
                label={translate('dispatchApplicationApp.serviceProvider.status')}
                id="service-provider-status"
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
              <Button tag={Link} id="cancel-save" data-cy="entityCreateCancelButton" to="/service-provider" replace color="info">
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

export default ServiceProviderUpdate;
