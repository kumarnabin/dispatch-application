import React, { useEffect } from 'react';
import { Link, useNavigate, useParams } from 'react-router-dom';
import { Button, Col, Row } from 'reactstrap';
import { ValidatedField, ValidatedForm } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { useAppDispatch, useAppSelector } from 'app/config/store';
import { Status } from 'app/shared/model/enumerations/status.model';
import { createEntity, getEntity, reset, updateEntity } from './service-provider.reducer';

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
    navigate('/service-provider');
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
              <ValidatedField label="Code" id="service-provider-code" name="code" data-cy="code" type="text" />
              <ValidatedField label="Phone" id="service-provider-phone" name="phone" data-cy="phone" type="text" />
              <ValidatedField label="Address" id="service-provider-address" name="address" data-cy="address" type="text" />
              <ValidatedField label="Status" id="service-provider-status" name="status" data-cy="status" type="select">
                {statusValues.map(status => (
                  <option value={status} key={status}>
                    {status}
                  </option>
                ))}
              </ValidatedField>
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
