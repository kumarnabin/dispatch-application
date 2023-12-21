import React, { useState, useEffect } from 'react';
import { Link, useNavigate, useParams } from 'react-router-dom';
import { Button, Row, Col, FormText } from 'reactstrap';
import { isNumber, ValidatedField, ValidatedForm } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { convertDateTimeFromServer, convertDateTimeToServer, displayDefaultDateTime } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { ITeam } from 'app/shared/model/team.model';
import { getEntities as getTeams } from 'app/entities/team/team.reducer';
import { ICustomer } from 'app/shared/model/customer.model';
import { getEntity, updateEntity, createEntity, reset } from './customer.reducer';

export const CustomerUpdate = () => {
  const dispatch = useAppDispatch();

  const navigate = useNavigate();

  const { id } = useParams<'id'>();
  const isNew = id === undefined;

  const teams = useAppSelector(state => state.team.entities);
  const customerEntity = useAppSelector(state => state.customer.entity);
  const loading = useAppSelector(state => state.customer.loading);
  const updating = useAppSelector(state => state.customer.updating);
  const updateSuccess = useAppSelector(state => state.customer.updateSuccess);

  const handleClose = () => {
    navigate('/customer');
  };

  useEffect(() => {
    if (isNew) {
      dispatch(reset());
    } else {
      dispatch(getEntity(id));
    }

    dispatch(getTeams({}));
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
      ...customerEntity,
      ...values,
      team: teams.find(it => it.id.toString() === values.team.toString()),
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
          ...customerEntity,
          publicationDate: convertDateTimeFromServer(customerEntity.publicationDate),
          team: customerEntity?.team?.id,
        };

  return (
    <div>
      <Row className="justify-content-center">
        <Col md="8">
          <h2 id="dispatchApplicationApp.customer.home.createOrEditLabel" data-cy="CustomerCreateUpdateHeading">
            Create or edit a Customer
          </h2>
        </Col>
      </Row>
      <Row className="justify-content-center">
        <Col md="8">
          {loading ? (
            <p>Loading...</p>
          ) : (
            <ValidatedForm defaultValues={defaultValues()} onSubmit={saveEntity}>
              {!isNew ? <ValidatedField name="id" required readOnly id="customer-id" label="ID" validate={{ required: true }} /> : null}
              <ValidatedField label="Voice" id="customer-voice" name="voice" data-cy="voice" type="text" />
              <ValidatedField label="Data" id="customer-data" name="data" data-cy="data" type="text" />
              <ValidatedField label="Iptv" id="customer-iptv" name="iptv" data-cy="iptv" type="text" />
              <ValidatedField label="Customer Name" id="customer-customerName" name="customerName" data-cy="customerName" type="text" />
              <ValidatedField label="Contact No" id="customer-contactNo" name="contactNo" data-cy="contactNo" type="text" />
              <ValidatedField label="Olt Port" id="customer-oltPort" name="oltPort" data-cy="oltPort" type="text" />
              <ValidatedField label="Reg Date" id="customer-regDate" name="regDate" data-cy="regDate" type="text" />
              <ValidatedField label="Fap Port" id="customer-fapPort" name="fapPort" data-cy="fapPort" type="text" />
              <ValidatedField label="Cpe Sn" id="customer-cpeSn" name="cpeSn" data-cy="cpeSn" type="text" />
              <ValidatedField label="Cpe Rx" id="customer-cpeRx" name="cpeRx" data-cy="cpeRx" type="text" />
              <ValidatedField label="Complain" id="customer-complain" name="complain" data-cy="complain" type="text" />
              <ValidatedField label="Remark" id="customer-remark" name="remark" data-cy="remark" type="text" />
              <ValidatedField label="Status" id="customer-status" name="status" data-cy="status" type="text" />
              <ValidatedField label="Location" id="customer-location" name="location" data-cy="location" type="text" />
              <ValidatedField label="Print Date" id="customer-printDate" name="printDate" data-cy="printDate" type="date" />
              <ValidatedField
                label="Publication Date"
                id="customer-publicationDate"
                name="publicationDate"
                data-cy="publicationDate"
                type="datetime-local"
                placeholder="YYYY-MM-DD HH:mm"
              />
              <ValidatedField id="customer-team" name="team" data-cy="team" label="Team" type="select">
                <option value="" key="0" />
                {teams
                  ? teams.map(otherEntity => (
                      <option value={otherEntity.id} key={otherEntity.id}>
                        {otherEntity.id}
                      </option>
                    ))
                  : null}
              </ValidatedField>
              <Button tag={Link} id="cancel-save" data-cy="entityCreateCancelButton" to="/customer" replace color="info">
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

export default CustomerUpdate;
