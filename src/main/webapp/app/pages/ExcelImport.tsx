import React, { useEffect, useState } from 'react';
import { Link, useNavigate } from 'react-router-dom';
import { Button, Col, Row } from 'reactstrap';
import { ValidatedBlobField, ValidatedForm } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { useAppDispatch, useAppSelector } from 'app/config/store';

export const ExcelImport = () => {
  const dispatch = useAppDispatch();

  const navigate = useNavigate();

  const loading = useAppSelector(state => state.excelFile.loading);
  const updating = useAppSelector(state => state.excelFile.updating);
  const updateSuccess = useAppSelector(state => state.excelFile.updateSuccess);
  const documentEntity = useAppSelector(state => state.excelFile.entity);

  const handleClose = () => {
    navigate('/document');
  };

  useEffect(() => {
    console.log(dispatch);
    if (updateSuccess) {
      handleClose();
    }
  }, [updateSuccess]);

  // eslint-disable-next-line complexity
  const saveEntity = values => {
    // values.preventDefault()
    const entity = {
      ...documentEntity,
      ...values,
    };
    console.log(values);
    console.log(entity);
  };

  const defaultValues = () => documentEntity;
  return (
    <div>
      <Row className="justify-content-center">
        <Col md="8">
          <h2 id="dispatchApplicationApp.document.home.createOrEditLabel" data-cy="DocumentCreateUpdateHeading">
            Excel Import
          </h2>
        </Col>
      </Row>
      <Row className="justify-content-center">
        <Col md="8">
          {loading ? (
            <p>Loading...</p>
          ) : (
            <ValidatedForm defaultValues={defaultValues()} onSubmit={saveEntity}>
              <ValidatedBlobField type={'file'} label="File" id="document-file" name="file" data-cy="file" accept=".xls, .xlsx, .csv" />
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

export default ExcelImport;
