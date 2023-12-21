import React, { useState, useEffect } from 'react';
import { Link, useNavigate, useParams } from 'react-router-dom';
import { Button, Row, Col, FormText } from 'reactstrap';
import { isNumber, ValidatedField, ValidatedForm } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { convertDateTimeFromServer, convertDateTimeToServer, displayDefaultDateTime } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { ITeam } from 'app/shared/model/team.model';
import { getEntity, updateEntity, createEntity, reset } from './team.reducer';

export const TeamUpdate = () => {
  const dispatch = useAppDispatch();

  const navigate = useNavigate();

  const { id } = useParams<'id'>();
  const isNew = id === undefined;

  const teamEntity = useAppSelector(state => state.team.entity);
  const loading = useAppSelector(state => state.team.loading);
  const updating = useAppSelector(state => state.team.updating);
  const updateSuccess = useAppSelector(state => state.team.updateSuccess);

  const handleClose = () => {
    navigate('/team');
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
    values.publicationDate = convertDateTimeToServer(values.publicationDate);

    const entity = {
      ...teamEntity,
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
      ? {
          publicationDate: displayDefaultDateTime(),
        }
      : {
          ...teamEntity,
          publicationDate: convertDateTimeFromServer(teamEntity.publicationDate),
        };

  return (
    <div>
      <Row className="justify-content-center">
        <Col md="8">
          <h2 id="dispatchApplicationApp.team.home.createOrEditLabel" data-cy="TeamCreateUpdateHeading">
            Create or edit a Team
          </h2>
        </Col>
      </Row>
      <Row className="justify-content-center">
        <Col md="8">
          {loading ? (
            <p>Loading...</p>
          ) : (
            <ValidatedForm defaultValues={defaultValues()} onSubmit={saveEntity}>
              {!isNew ? <ValidatedField name="id" required readOnly id="team-id" label="ID" validate={{ required: true }} /> : null}
              <ValidatedField label="Team No" id="team-teamNo" name="teamNo" data-cy="teamNo" type="text" />
              <ValidatedField label="Supervisor" id="team-supervisor" name="supervisor" data-cy="supervisor" type="text" />
              <ValidatedField
                label="Supervisor Phone No"
                id="team-supervisorPhoneNo"
                name="supervisorPhoneNo"
                data-cy="supervisorPhoneNo"
                type="text"
              />
              <ValidatedField label="Team Leader" id="team-teamLeader" name="teamLeader" data-cy="teamLeader" type="text" />
              <ValidatedField
                label="Team Leader Phone"
                id="team-teamLeaderPhone"
                name="teamLeaderPhone"
                data-cy="teamLeaderPhone"
                type="text"
              />
              <ValidatedField
                label="Publication Date"
                id="team-publicationDate"
                name="publicationDate"
                data-cy="publicationDate"
                type="datetime-local"
                placeholder="YYYY-MM-DD HH:mm"
              />
              <Button tag={Link} id="cancel-save" data-cy="entityCreateCancelButton" to="/team" replace color="info">
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

export default TeamUpdate;
