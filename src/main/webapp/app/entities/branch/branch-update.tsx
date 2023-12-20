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
import { IBranchCircuit } from 'app/shared/model/branch-circuit.model';
import { getEntities as getBranchCircuits } from 'app/entities/branch-circuit/branch-circuit.reducer';
import { IBranch } from 'app/shared/model/branch.model';
import { getEntity, updateEntity, createEntity, reset } from './branch.reducer';

export const BranchUpdate = () => {
  const dispatch = useAppDispatch();

  const navigate = useNavigate();

  const { id } = useParams<'id'>();
  const isNew = id === undefined;

  const serviceProviders = useAppSelector(state => state.serviceProvider.entities);
  const branchCircuits = useAppSelector(state => state.branchCircuit.entities);
  const branchEntity = useAppSelector(state => state.branch.entity);
  const loading = useAppSelector(state => state.branch.loading);
  const updating = useAppSelector(state => state.branch.updating);
  const updateSuccess = useAppSelector(state => state.branch.updateSuccess);

  const handleClose = () => {
    navigate('/branch');
  };

  useEffect(() => {
    if (isNew) {
      dispatch(reset());
    } else {
      dispatch(getEntity(id));
    }

    dispatch(getServiceProviders({}));
    dispatch(getBranchCircuits({}));
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
      ...branchEntity,
      ...values,
      serviceProvider: serviceProviders.find(it => it.id.toString() === values.serviceProvider.toString()),
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
          ...branchEntity,
          publicationDate: convertDateTimeFromServer(branchEntity.publicationDate),
          serviceProvider: branchEntity?.serviceProvider?.id,
        };

  return (
    <div>
      <Row className="justify-content-center">
        <Col md="8">
          <h2 id="dispatchApplicationApp.branch.home.createOrEditLabel" data-cy="BranchCreateUpdateHeading">
            Create or edit a Branch
          </h2>
        </Col>
      </Row>
      <Row className="justify-content-center">
        <Col md="8">
          {loading ? (
            <p>Loading...</p>
          ) : (
            <ValidatedForm defaultValues={defaultValues()} onSubmit={saveEntity}>
              {!isNew ? <ValidatedField name="id" required readOnly id="branch-id" label="ID" validate={{ required: true }} /> : null}
              <ValidatedField label="Name" id="branch-name" name="name" data-cy="name" type="text" />
              <ValidatedField
                label="Publication Date"
                id="branch-publicationDate"
                name="publicationDate"
                data-cy="publicationDate"
                type="datetime-local"
                placeholder="YYYY-MM-DD HH:mm"
              />
              <ValidatedField
                id="branch-serviceProvider"
                name="serviceProvider"
                data-cy="serviceProvider"
                label="Service Provider"
                type="select"
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
              <Button tag={Link} id="cancel-save" data-cy="entityCreateCancelButton" to="/branch" replace color="info">
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

export default BranchUpdate;
