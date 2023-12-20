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
import { IBranch } from 'app/shared/model/branch.model';
import { getEntities as getBranches } from 'app/entities/branch/branch.reducer';
import { IBranchCircuit } from 'app/shared/model/branch-circuit.model';
import { getEntity, updateEntity, createEntity, reset } from './branch-circuit.reducer';

export const BranchCircuitUpdate = () => {
  const dispatch = useAppDispatch();

  const navigate = useNavigate();

  const { id } = useParams<'id'>();
  const isNew = id === undefined;

  const masterCircuits = useAppSelector(state => state.masterCircuit.entities);
  const branches = useAppSelector(state => state.branch.entities);
  const branchCircuitEntity = useAppSelector(state => state.branchCircuit.entity);
  const loading = useAppSelector(state => state.branchCircuit.loading);
  const updating = useAppSelector(state => state.branchCircuit.updating);
  const updateSuccess = useAppSelector(state => state.branchCircuit.updateSuccess);

  const handleClose = () => {
    navigate('/branch-circuit');
  };

  useEffect(() => {
    if (isNew) {
      dispatch(reset());
    } else {
      dispatch(getEntity(id));
    }

    dispatch(getMasterCircuits({}));
    dispatch(getBranches({}));
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
      ...branchCircuitEntity,
      ...values,
      branches: mapIdList(values.branches),
      masterCircuit: masterCircuits.find(it => it.id.toString() === values.masterCircuit.toString()),
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
          ...branchCircuitEntity,
          publicationDate: convertDateTimeFromServer(branchCircuitEntity.publicationDate),
          masterCircuit: branchCircuitEntity?.masterCircuit?.id,
          branches: branchCircuitEntity?.branches?.map(e => e.id.toString()),
        };

  return (
    <div>
      <Row className="justify-content-center">
        <Col md="8">
          <h2 id="dispatchApplicationApp.branchCircuit.home.createOrEditLabel" data-cy="BranchCircuitCreateUpdateHeading">
            Create or edit a Branch Circuit
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
                <ValidatedField name="id" required readOnly id="branch-circuit-id" label="ID" validate={{ required: true }} />
              ) : null}
              <ValidatedField label="Title" id="branch-circuit-title" name="title" data-cy="title" type="text" />
              <ValidatedField
                label="Publication Date"
                id="branch-circuit-publicationDate"
                name="publicationDate"
                data-cy="publicationDate"
                type="datetime-local"
                placeholder="YYYY-MM-DD HH:mm"
              />
              <ValidatedField
                id="branch-circuit-masterCircuit"
                name="masterCircuit"
                data-cy="masterCircuit"
                label="Master Circuit"
                type="select"
              >
                <option value="" key="0" />
                {masterCircuits
                  ? masterCircuits.map(otherEntity => (
                      <option value={otherEntity.id} key={otherEntity.id}>
                        {otherEntity.id}
                      </option>
                    ))
                  : null}
              </ValidatedField>
              <ValidatedField label="Branch" id="branch-circuit-branch" data-cy="branch" type="select" multiple name="branches">
                <option value="" key="0" />
                {branches
                  ? branches.map(otherEntity => (
                      <option value={otherEntity.id} key={otherEntity.id}>
                        {otherEntity.id}
                      </option>
                    ))
                  : null}
              </ValidatedField>
              <Button tag={Link} id="cancel-save" data-cy="entityCreateCancelButton" to="/branch-circuit" replace color="info">
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

export default BranchCircuitUpdate;
