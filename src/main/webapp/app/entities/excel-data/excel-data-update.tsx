import React, { useState, useEffect } from 'react';
import { Link, useNavigate, useParams } from 'react-router-dom';
import { Button, Row, Col, FormText } from 'reactstrap';
import { isNumber, ValidatedField, ValidatedForm } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { convertDateTimeFromServer, convertDateTimeToServer, displayDefaultDateTime } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { IExcelData } from 'app/shared/model/excel-data.model';
import { getEntity, updateEntity, createEntity, reset } from './excel-data.reducer';

export const ExcelDataUpdate = () => {
  const dispatch = useAppDispatch();

  const navigate = useNavigate();

  const { id } = useParams<'id'>();
  const isNew = id === undefined;

  const excelDataEntity = useAppSelector(state => state.excelData.entity);
  const loading = useAppSelector(state => state.excelData.loading);
  const updating = useAppSelector(state => state.excelData.updating);
  const updateSuccess = useAppSelector(state => state.excelData.updateSuccess);

  const handleClose = () => {
    navigate('/excel-data');
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
      ...excelDataEntity,
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
          ...excelDataEntity,
          publicationDate: convertDateTimeFromServer(excelDataEntity.publicationDate),
        };

  return (
    <div>
      <Row className="justify-content-center">
        <Col md="8">
          <h2 id="dispatchApplicationApp.excelData.home.createOrEditLabel" data-cy="ExcelDataCreateUpdateHeading">
            Create or edit a Excel Data
          </h2>
        </Col>
      </Row>
      <Row className="justify-content-center">
        <Col md="8">
          {loading ? (
            <p>Loading...</p>
          ) : (
            <ValidatedForm defaultValues={defaultValues()} onSubmit={saveEntity}>
              {!isNew ? <ValidatedField name="id" required readOnly id="excel-data-id" label="ID" validate={{ required: true }} /> : null}
              <ValidatedField label="Column 1" id="excel-data-column1" name="column1" data-cy="column1" type="text" />
              <ValidatedField label="Column 2" id="excel-data-column2" name="column2" data-cy="column2" type="text" />
              <ValidatedField label="Column 3" id="excel-data-column3" name="column3" data-cy="column3" type="text" />
              <ValidatedField label="Column 4" id="excel-data-column4" name="column4" data-cy="column4" type="text" />
              <ValidatedField label="Column 5" id="excel-data-column5" name="column5" data-cy="column5" type="text" />
              <ValidatedField label="Column 6" id="excel-data-column6" name="column6" data-cy="column6" type="text" />
              <ValidatedField label="Column 7" id="excel-data-column7" name="column7" data-cy="column7" type="text" />
              <ValidatedField label="Column 8" id="excel-data-column8" name="column8" data-cy="column8" type="text" />
              <ValidatedField label="Column 9" id="excel-data-column9" name="column9" data-cy="column9" type="text" />
              <ValidatedField label="Column 10" id="excel-data-column10" name="column10" data-cy="column10" type="text" />
              <ValidatedField label="Column 11" id="excel-data-column11" name="column11" data-cy="column11" type="text" />
              <ValidatedField label="Column 12" id="excel-data-column12" name="column12" data-cy="column12" type="text" />
              <ValidatedField label="Column 13" id="excel-data-column13" name="column13" data-cy="column13" type="text" />
              <ValidatedField label="Column 14" id="excel-data-column14" name="column14" data-cy="column14" type="text" />
              <ValidatedField label="Column 15" id="excel-data-column15" name="column15" data-cy="column15" type="text" />
              <ValidatedField label="Column 16" id="excel-data-column16" name="column16" data-cy="column16" type="text" />
              <ValidatedField label="Column 17" id="excel-data-column17" name="column17" data-cy="column17" type="text" />
              <ValidatedField label="Column 18" id="excel-data-column18" name="column18" data-cy="column18" type="text" />
              <ValidatedField label="Column 19" id="excel-data-column19" name="column19" data-cy="column19" type="text" />
              <ValidatedField label="Column 20" id="excel-data-column20" name="column20" data-cy="column20" type="text" />
              <ValidatedField label="Column 21" id="excel-data-column21" name="column21" data-cy="column21" type="text" />
              <ValidatedField label="Column 22" id="excel-data-column22" name="column22" data-cy="column22" type="text" />
              <ValidatedField label="Column 23" id="excel-data-column23" name="column23" data-cy="column23" type="text" />
              <ValidatedField label="Column 24" id="excel-data-column24" name="column24" data-cy="column24" type="text" />
              <ValidatedField label="Column 25" id="excel-data-column25" name="column25" data-cy="column25" type="text" />
              <ValidatedField label="Column 26" id="excel-data-column26" name="column26" data-cy="column26" type="text" />
              <ValidatedField label="Column 27" id="excel-data-column27" name="column27" data-cy="column27" type="text" />
              <ValidatedField
                label="Publication Date"
                id="excel-data-publicationDate"
                name="publicationDate"
                data-cy="publicationDate"
                type="datetime-local"
                placeholder="YYYY-MM-DD HH:mm"
              />
              <Button tag={Link} id="cancel-save" data-cy="entityCreateCancelButton" to="/excel-data" replace color="info">
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

export default ExcelDataUpdate;
