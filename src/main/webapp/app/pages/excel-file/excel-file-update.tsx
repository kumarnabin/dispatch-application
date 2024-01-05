import React, { useState, useEffect } from 'react';
import { Link, useNavigate, useParams } from 'react-router-dom';
import { Button, Row, Col, FormText } from 'reactstrap';
import { isNumber, ValidatedField, ValidatedForm } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { convertDateTimeFromServer, convertDateTimeToServer, displayDefaultDateTime } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { IExcelData } from 'app/shared/model/excel-file.model';
import { getEntity, updateEntity, createEntity, reset } from './excel-file.reducer';

export const ExcelDataUpdate = () => {
  const dispatch = useAppDispatch();

  const navigate = useNavigate();

  const { id } = useParams<'id'>();
  const isNew = id === undefined;

  const excelFileEntity = useAppSelector(state => state.excelFile.entity);
  const loading = useAppSelector(state => state.excelFile.loading);
  const updating = useAppSelector(state => state.excelFile.updating);
  const updateSuccess = useAppSelector(state => state.excelFile.updateSuccess);

  const handleClose = () => {
    navigate('/excel-file');
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
      ...excelFileEntity,
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
          ...excelFileEntity,
          publicationDate: convertDateTimeFromServer(excelFileEntity.publicationDate),
        };

  return (
    <div>
      <Row className="justify-content-center">
        <Col md="8">
          <h2 id="dispatchApplicationApp.excelFile.home.createOrEditLabel" data-cy="ExcelDataCreateUpdateHeading">
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
              {!isNew ? <ValidatedField name="id" required readOnly id="excel-file-id" label="ID" validate={{ required: true }} /> : null}
              <ValidatedField label="Column 1" id="excel-file-column1" name="column1" data-cy="column1" type="text" />
              <ValidatedField label="Column 2" id="excel-file-column2" name="column2" data-cy="column2" type="text" />
              <ValidatedField label="Column 3" id="excel-file-column3" name="column3" data-cy="column3" type="text" />
              <ValidatedField label="Column 4" id="excel-file-column4" name="column4" data-cy="column4" type="text" />
              <ValidatedField label="Column 5" id="excel-file-column5" name="column5" data-cy="column5" type="text" />
              <ValidatedField label="Column 6" id="excel-file-column6" name="column6" data-cy="column6" type="text" />
              <ValidatedField label="Column 7" id="excel-file-column7" name="column7" data-cy="column7" type="text" />
              <ValidatedField label="Column 8" id="excel-file-column8" name="column8" data-cy="column8" type="text" />
              <ValidatedField label="Column 9" id="excel-file-column9" name="column9" data-cy="column9" type="text" />
              <ValidatedField label="Column 10" id="excel-file-column10" name="column10" data-cy="column10" type="text" />
              <ValidatedField label="Column 11" id="excel-file-column11" name="column11" data-cy="column11" type="text" />
              <ValidatedField label="Column 12" id="excel-file-column12" name="column12" data-cy="column12" type="text" />
              <ValidatedField label="Column 13" id="excel-file-column13" name="column13" data-cy="column13" type="text" />
              <ValidatedField label="Column 14" id="excel-file-column14" name="column14" data-cy="column14" type="text" />
              <ValidatedField label="Column 15" id="excel-file-column15" name="column15" data-cy="column15" type="text" />
              <ValidatedField label="Column 16" id="excel-file-column16" name="column16" data-cy="column16" type="text" />
              <ValidatedField label="Column 17" id="excel-file-column17" name="column17" data-cy="column17" type="text" />
              <ValidatedField label="Column 18" id="excel-file-column18" name="column18" data-cy="column18" type="text" />
              <ValidatedField label="Column 19" id="excel-file-column19" name="column19" data-cy="column19" type="text" />
              <ValidatedField label="Column 20" id="excel-file-column20" name="column20" data-cy="column20" type="text" />
              <ValidatedField label="Column 21" id="excel-file-column21" name="column21" data-cy="column21" type="text" />
              <ValidatedField label="Column 22" id="excel-file-column22" name="column22" data-cy="column22" type="text" />
              <ValidatedField label="Column 23" id="excel-file-column23" name="column23" data-cy="column23" type="text" />
              <ValidatedField label="Column 24" id="excel-file-column24" name="column24" data-cy="column24" type="text" />
              <ValidatedField label="Column 25" id="excel-file-column25" name="column25" data-cy="column25" type="text" />
              <ValidatedField label="Column 26" id="excel-file-column26" name="column26" data-cy="column26" type="text" />
              <ValidatedField label="Column 27" id="excel-file-column27" name="column27" data-cy="column27" type="text" />
              <ValidatedField
                label="Publication Date"
                id="excel-file-publicationDate"
                name="publicationDate"
                data-cy="publicationDate"
                type="datetime-local"
                placeholder="YYYY-MM-DD HH:mm"
              />
              <Button tag={Link} id="cancel-save" data-cy="entityCreateCancelButton" to="/excel-file" replace color="info">
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
