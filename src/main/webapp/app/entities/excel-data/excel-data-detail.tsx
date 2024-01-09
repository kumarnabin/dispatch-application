import React, { useEffect } from 'react';
import { Link, useParams } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { Translate, TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { getEntity } from './excel-data.reducer';

export const ExcelDataDetail = () => {
  const dispatch = useAppDispatch();

  const { id } = useParams<'id'>();

  useEffect(() => {
    dispatch(getEntity(id));
  }, []);

  const excelDataEntity = useAppSelector(state => state.excelData.entity);
  return (
    <Row>
      <Col md="8">
        <h2 data-cy="excelDataDetailsHeading">
          <Translate contentKey="dispatchApplicationApp.excelData.detail.title">ExcelData</Translate>
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="id">
              <Translate contentKey="global.field.id">ID</Translate>
            </span>
          </dt>
          <dd>{excelDataEntity.id}</dd>
          <dt>
            <span id="column1">
              <Translate contentKey="dispatchApplicationApp.excelData.column1">Column 1</Translate>
            </span>
          </dt>
          <dd>{excelDataEntity.column1}</dd>
          <dt>
            <span id="column2">
              <Translate contentKey="dispatchApplicationApp.excelData.column2">Column 2</Translate>
            </span>
          </dt>
          <dd>{excelDataEntity.column2}</dd>
          <dt>
            <span id="column3">
              <Translate contentKey="dispatchApplicationApp.excelData.column3">Column 3</Translate>
            </span>
          </dt>
          <dd>{excelDataEntity.column3}</dd>
          <dt>
            <span id="column4">
              <Translate contentKey="dispatchApplicationApp.excelData.column4">Column 4</Translate>
            </span>
          </dt>
          <dd>{excelDataEntity.column4}</dd>
          <dt>
            <span id="column5">
              <Translate contentKey="dispatchApplicationApp.excelData.column5">Column 5</Translate>
            </span>
          </dt>
          <dd>{excelDataEntity.column5}</dd>
          <dt>
            <span id="column6">
              <Translate contentKey="dispatchApplicationApp.excelData.column6">Column 6</Translate>
            </span>
          </dt>
          <dd>{excelDataEntity.column6}</dd>
          <dt>
            <span id="column7">
              <Translate contentKey="dispatchApplicationApp.excelData.column7">Column 7</Translate>
            </span>
          </dt>
          <dd>{excelDataEntity.column7}</dd>
          <dt>
            <span id="column8">
              <Translate contentKey="dispatchApplicationApp.excelData.column8">Column 8</Translate>
            </span>
          </dt>
          <dd>{excelDataEntity.column8}</dd>
          <dt>
            <span id="column9">
              <Translate contentKey="dispatchApplicationApp.excelData.column9">Column 9</Translate>
            </span>
          </dt>
          <dd>{excelDataEntity.column9}</dd>
          <dt>
            <span id="column10">
              <Translate contentKey="dispatchApplicationApp.excelData.column10">Column 10</Translate>
            </span>
          </dt>
          <dd>{excelDataEntity.column10}</dd>
          <dt>
            <span id="column11">
              <Translate contentKey="dispatchApplicationApp.excelData.column11">Column 11</Translate>
            </span>
          </dt>
          <dd>{excelDataEntity.column11}</dd>
          <dt>
            <span id="column12">
              <Translate contentKey="dispatchApplicationApp.excelData.column12">Column 12</Translate>
            </span>
          </dt>
          <dd>{excelDataEntity.column12}</dd>
          <dt>
            <span id="column13">
              <Translate contentKey="dispatchApplicationApp.excelData.column13">Column 13</Translate>
            </span>
          </dt>
          <dd>{excelDataEntity.column13}</dd>
          <dt>
            <span id="column14">
              <Translate contentKey="dispatchApplicationApp.excelData.column14">Column 14</Translate>
            </span>
          </dt>
          <dd>{excelDataEntity.column14}</dd>
          <dt>
            <span id="column15">
              <Translate contentKey="dispatchApplicationApp.excelData.column15">Column 15</Translate>
            </span>
          </dt>
          <dd>{excelDataEntity.column15}</dd>
          <dt>
            <span id="column16">
              <Translate contentKey="dispatchApplicationApp.excelData.column16">Column 16</Translate>
            </span>
          </dt>
          <dd>{excelDataEntity.column16}</dd>
          <dt>
            <span id="column17">
              <Translate contentKey="dispatchApplicationApp.excelData.column17">Column 17</Translate>
            </span>
          </dt>
          <dd>{excelDataEntity.column17}</dd>
          <dt>
            <span id="column18">
              <Translate contentKey="dispatchApplicationApp.excelData.column18">Column 18</Translate>
            </span>
          </dt>
          <dd>{excelDataEntity.column18}</dd>
          <dt>
            <span id="column19">
              <Translate contentKey="dispatchApplicationApp.excelData.column19">Column 19</Translate>
            </span>
          </dt>
          <dd>{excelDataEntity.column19}</dd>
          <dt>
            <span id="column20">
              <Translate contentKey="dispatchApplicationApp.excelData.column20">Column 20</Translate>
            </span>
          </dt>
          <dd>{excelDataEntity.column20}</dd>
          <dt>
            <span id="column21">
              <Translate contentKey="dispatchApplicationApp.excelData.column21">Column 21</Translate>
            </span>
          </dt>
          <dd>{excelDataEntity.column21}</dd>
          <dt>
            <span id="column22">
              <Translate contentKey="dispatchApplicationApp.excelData.column22">Column 22</Translate>
            </span>
          </dt>
          <dd>{excelDataEntity.column22}</dd>
          <dt>
            <span id="column23">
              <Translate contentKey="dispatchApplicationApp.excelData.column23">Column 23</Translate>
            </span>
          </dt>
          <dd>{excelDataEntity.column23}</dd>
          <dt>
            <span id="column24">
              <Translate contentKey="dispatchApplicationApp.excelData.column24">Column 24</Translate>
            </span>
          </dt>
          <dd>{excelDataEntity.column24}</dd>
          <dt>
            <span id="column25">
              <Translate contentKey="dispatchApplicationApp.excelData.column25">Column 25</Translate>
            </span>
          </dt>
          <dd>{excelDataEntity.column25}</dd>
          <dt>
            <span id="column26">
              <Translate contentKey="dispatchApplicationApp.excelData.column26">Column 26</Translate>
            </span>
          </dt>
          <dd>{excelDataEntity.column26}</dd>
          <dt>
            <span id="column27">
              <Translate contentKey="dispatchApplicationApp.excelData.column27">Column 27</Translate>
            </span>
          </dt>
          <dd>{excelDataEntity.column27}</dd>
          <dt>
            <span id="publicationDate">
              <Translate contentKey="dispatchApplicationApp.excelData.publicationDate">Publication Date</Translate>
            </span>
          </dt>
          <dd>
            {excelDataEntity.publicationDate ? (
              <TextFormat value={excelDataEntity.publicationDate} type="date" format={APP_DATE_FORMAT} />
            ) : null}
          </dd>
        </dl>
        <Button tag={Link} to="/excel-data" replace color="info" data-cy="entityDetailsBackButton">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/excel-data/${excelDataEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

export default ExcelDataDetail;
