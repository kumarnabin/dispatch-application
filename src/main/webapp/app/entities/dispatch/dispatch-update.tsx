import React, { useState, useEffect } from 'react';
import { Link, useNavigate, useParams } from 'react-router-dom';
import { Button, Row, Col, FormText } from 'reactstrap';
import { isNumber, Translate, translate, ValidatedField, ValidatedForm } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { convertDateTimeFromServer, convertDateTimeToServer, displayDefaultDateTime } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { ITeam } from 'app/shared/model/team.model';
import { getEntities as getTeams } from 'app/entities/team/team.reducer';
import { IDispatch } from 'app/shared/model/dispatch.model';
import { Status } from 'app/shared/model/enumerations/status.model';
import { getEntity, updateEntity, createEntity, reset } from './dispatch.reducer';

export const DispatchUpdate = () => {
  const dispatch = useAppDispatch();

  const navigate = useNavigate();

  const { id } = useParams<'id'>();
  const isNew = id === undefined;

  const teams = useAppSelector(state => state.team.entities);
  const dispatchEntity = useAppSelector(state => state.dispatch.entity);
  const loading = useAppSelector(state => state.dispatch.loading);
  const updating = useAppSelector(state => state.dispatch.updating);
  const updateSuccess = useAppSelector(state => state.dispatch.updateSuccess);
  const statusValues = Object.keys(Status);

  const handleClose = () => {
    navigate('/dispatch' + location.search);
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
      ...dispatchEntity,
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
          status: 'OPEN',
          ...dispatchEntity,
          publicationDate: convertDateTimeFromServer(dispatchEntity.publicationDate),
          team: dispatchEntity?.team?.id,
        };

  return (
    <div>
      <Row className="justify-content-center">
        <Col md="8">
          <h2 id="dispatchApplicationApp.dispatch.home.createOrEditLabel" data-cy="DispatchCreateUpdateHeading">
            <Translate contentKey="dispatchApplicationApp.dispatch.home.createOrEditLabel">Create or edit a Dispatch</Translate>
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
                  id="dispatch-id"
                  label={translate('global.field.id')}
                  validate={{ required: true }}
                />
              ) : null}
              <ValidatedField
                label={translate('dispatchApplicationApp.dispatch.voice')}
                id="dispatch-voice"
                name="voice"
                data-cy="voice"
                type="text"
              />
              <ValidatedField
                label={translate('dispatchApplicationApp.dispatch.data')}
                id="dispatch-data"
                name="data"
                data-cy="data"
                type="text"
              />
              <ValidatedField
                label={translate('dispatchApplicationApp.dispatch.iptv')}
                id="dispatch-iptv"
                name="iptv"
                data-cy="iptv"
                type="text"
              />
              <ValidatedField
                label={translate('dispatchApplicationApp.dispatch.customerName')}
                id="dispatch-customerName"
                name="customerName"
                data-cy="customerName"
                type="text"
              />
              <ValidatedField
                label={translate('dispatchApplicationApp.dispatch.contactNo')}
                id="dispatch-contactNo"
                name="contactNo"
                data-cy="contactNo"
                type="text"
              />
              <ValidatedField
                label={translate('dispatchApplicationApp.dispatch.oltPort')}
                id="dispatch-oltPort"
                name="oltPort"
                data-cy="oltPort"
                type="text"
              />
              <ValidatedField
                label={translate('dispatchApplicationApp.dispatch.regDate')}
                id="dispatch-regDate"
                name="regDate"
                data-cy="regDate"
                type="text"
              />
              <ValidatedField
                label={translate('dispatchApplicationApp.dispatch.fapPort')}
                id="dispatch-fapPort"
                name="fapPort"
                data-cy="fapPort"
                type="text"
              />
              <ValidatedField
                label={translate('dispatchApplicationApp.dispatch.cpeSn')}
                id="dispatch-cpeSn"
                name="cpeSn"
                data-cy="cpeSn"
                type="text"
              />
              <ValidatedField
                label={translate('dispatchApplicationApp.dispatch.cpeRx')}
                id="dispatch-cpeRx"
                name="cpeRx"
                data-cy="cpeRx"
                type="text"
              />
              <ValidatedField
                label={translate('dispatchApplicationApp.dispatch.complain')}
                id="dispatch-complain"
                name="complain"
                data-cy="complain"
                type="text"
              />
              <ValidatedField
                label={translate('dispatchApplicationApp.dispatch.remark')}
                id="dispatch-remark"
                name="remark"
                data-cy="remark"
                type="text"
              />
              <ValidatedField
                label={translate('dispatchApplicationApp.dispatch.status')}
                id="dispatch-status"
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
                label={translate('dispatchApplicationApp.dispatch.location')}
                id="dispatch-location"
                name="location"
                data-cy="location"
                type="text"
              />
              <ValidatedField
                label={translate('dispatchApplicationApp.dispatch.printDate')}
                id="dispatch-printDate"
                name="printDate"
                data-cy="printDate"
                type="date"
              />
              <ValidatedField
                label={translate('dispatchApplicationApp.dispatch.publicationDate')}
                id="dispatch-publicationDate"
                name="publicationDate"
                data-cy="publicationDate"
                type="datetime-local"
                placeholder="YYYY-MM-DD HH:mm"
              />
              <ValidatedField
                id="dispatch-team"
                name="team"
                data-cy="team"
                label={translate('dispatchApplicationApp.dispatch.team')}
                type="select"
              >
                <option value="" key="0" />
                {teams
                  ? teams.map(otherEntity => (
                      <option value={otherEntity.id} key={otherEntity.id}>
                        {otherEntity.id}
                      </option>
                    ))
                  : null}
              </ValidatedField>
              <Button tag={Link} id="cancel-save" data-cy="entityCreateCancelButton" to="/dispatch" replace color="info">
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

export default DispatchUpdate;
