import React, { useState, useEffect } from 'react';
import { Link, useNavigate, useParams } from 'react-router-dom';
import { Button, Row, Col, FormText } from 'reactstrap';
import { isNumber, Translate, translate, ValidatedField, ValidatedForm } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { convertDateTimeFromServer, convertDateTimeToServer, displayDefaultDateTime } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { IOlt } from 'app/shared/model/olt.model';
import { getEntities as getOlts } from 'app/entities/olt/olt.reducer';
import { IArea } from 'app/shared/model/area.model';
import { Status } from 'app/shared/model/enumerations/status.model';
import { getEntity, updateEntity, createEntity, reset } from './area.reducer';

export const AreaUpdate = () => {
  const dispatch = useAppDispatch();

  const navigate = useNavigate();

  const { id } = useParams<'id'>();
  const isNew = id === undefined;

  const olts = useAppSelector(state => state.olt.entities);
  const areaEntity = useAppSelector(state => state.area.entity);
  const loading = useAppSelector(state => state.area.loading);
  const updating = useAppSelector(state => state.area.updating);
  const updateSuccess = useAppSelector(state => state.area.updateSuccess);
  const statusValues = Object.keys(Status);

  const handleClose = () => {
    navigate('/area' + location.search);
  };

  useEffect(() => {
    if (isNew) {
      dispatch(reset());
    } else {
      dispatch(getEntity(id));
    }

    dispatch(getOlts({}));
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
      ...areaEntity,
      ...values,
      olt: olts.find(it => it.id.toString() === values.olt.toString()),
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
          ...areaEntity,
          olt: areaEntity?.olt?.id,
        };

  return (
    <div>
      <Row className="justify-content-center">
        <Col md="8">
          <h2 id="dispatchApplicationApp.area.home.createOrEditLabel" data-cy="AreaCreateUpdateHeading">
            <Translate contentKey="dispatchApplicationApp.area.home.createOrEditLabel">Create or edit a Area</Translate>
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
                  id="area-id"
                  label={translate('global.field.id')}
                  validate={{ required: true }}
                />
              ) : null}
              <ValidatedField label={translate('dispatchApplicationApp.area.name')} id="area-name" name="name" data-cy="name" type="text" />
              <ValidatedField
                label={translate('dispatchApplicationApp.area.code')}
                id="area-code"
                name="code"
                data-cy="code"
                type="text"
                validate={{}}
              />
              <ValidatedField
                label={translate('dispatchApplicationApp.area.detail')}
                id="area-detail"
                name="detail"
                data-cy="detail"
                type="text"
              />
              <ValidatedField
                label={translate('dispatchApplicationApp.area.status')}
                id="area-status"
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
              <ValidatedField id="area-olt" name="olt" data-cy="olt" label={translate('dispatchApplicationApp.area.olt')} type="select">
                <option value="" key="0" />
                {olts
                  ? olts.map(otherEntity => (
                      <option value={otherEntity.id} key={otherEntity.id}>
                        {otherEntity.id}
                      </option>
                    ))
                  : null}
              </ValidatedField>
              <Button tag={Link} id="cancel-save" data-cy="entityCreateCancelButton" to="/area" replace color="info">
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

export default AreaUpdate;
