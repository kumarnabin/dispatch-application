import React, { useEffect } from 'react';
import { Link, useParams } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { getEntity } from './excel-file.reducer';

export const ExcelDataDetail = () => {
  const dispatch = useAppDispatch();

  const { id } = useParams<'id'>();

  useEffect(() => {
    dispatch(getEntity(id));
  }, []);

  const excelFileEntity = useAppSelector(state => state.excelFile.entity);
  return (
    <Row>
      <Col md="8">
        <h2 data-cy="excelFileDetailsHeading">Excel Data</h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="id">ID</span>
          </dt>
          <dd>{excelFileEntity.id}</dd>
          <dt>
            <span id="column1">Column 1</span>
          </dt>
          <dd>{excelFileEntity.column1}</dd>
          <dt>
            <span id="column2">Column 2</span>
          </dt>
          <dd>{excelFileEntity.column2}</dd>
          <dt>
            <span id="column3">Column 3</span>
          </dt>
          <dd>{excelFileEntity.column3}</dd>
          <dt>
            <span id="column4">Column 4</span>
          </dt>
          <dd>{excelFileEntity.column4}</dd>
          <dt>
            <span id="column5">Column 5</span>
          </dt>
          <dd>{excelFileEntity.column5}</dd>
          <dt>
            <span id="column6">Column 6</span>
          </dt>
          <dd>{excelFileEntity.column6}</dd>
          <dt>
            <span id="column7">Column 7</span>
          </dt>
          <dd>{excelFileEntity.column7}</dd>
          <dt>
            <span id="column8">Column 8</span>
          </dt>
          <dd>{excelFileEntity.column8}</dd>
          <dt>
            <span id="column9">Column 9</span>
          </dt>
          <dd>{excelFileEntity.column9}</dd>
          <dt>
            <span id="column10">Column 10</span>
          </dt>
          <dd>{excelFileEntity.column10}</dd>
          <dt>
            <span id="column11">Column 11</span>
          </dt>
          <dd>{excelFileEntity.column11}</dd>
          <dt>
            <span id="column12">Column 12</span>
          </dt>
          <dd>{excelFileEntity.column12}</dd>
          <dt>
            <span id="column13">Column 13</span>
          </dt>
          <dd>{excelFileEntity.column13}</dd>
          <dt>
            <span id="column14">Column 14</span>
          </dt>
          <dd>{excelFileEntity.column14}</dd>
          <dt>
            <span id="column15">Column 15</span>
          </dt>
          <dd>{excelFileEntity.column15}</dd>
          <dt>
            <span id="column16">Column 16</span>
          </dt>
          <dd>{excelFileEntity.column16}</dd>
          <dt>
            <span id="column17">Column 17</span>
          </dt>
          <dd>{excelFileEntity.column17}</dd>
          <dt>
            <span id="column18">Column 18</span>
          </dt>
          <dd>{excelFileEntity.column18}</dd>
          <dt>
            <span id="column19">Column 19</span>
          </dt>
          <dd>{excelFileEntity.column19}</dd>
          <dt>
            <span id="column20">Column 20</span>
          </dt>
          <dd>{excelFileEntity.column20}</dd>
          <dt>
            <span id="column21">Column 21</span>
          </dt>
          <dd>{excelFileEntity.column21}</dd>
          <dt>
            <span id="column22">Column 22</span>
          </dt>
          <dd>{excelFileEntity.column22}</dd>
          <dt>
            <span id="column23">Column 23</span>
          </dt>
          <dd>{excelFileEntity.column23}</dd>
          <dt>
            <span id="column24">Column 24</span>
          </dt>
          <dd>{excelFileEntity.column24}</dd>
          <dt>
            <span id="column25">Column 25</span>
          </dt>
          <dd>{excelFileEntity.column25}</dd>
          <dt>
            <span id="column26">Column 26</span>
          </dt>
          <dd>{excelFileEntity.column26}</dd>
          <dt>
            <span id="column27">Column 27</span>
          </dt>
          <dd>{excelFileEntity.column27}</dd>
          <dt>
            <span id="publicationDate">Publication Date</span>
          </dt>
          <dd>
            {excelFileEntity.publicationDate ? (
              <TextFormat value={excelFileEntity.publicationDate} type="date" format={APP_DATE_FORMAT} />
            ) : null}
          </dd>
        </dl>
        <Button tag={Link} to="/excel-file" replace color="info" data-cy="entityDetailsBackButton">
          <FontAwesomeIcon icon="arrow-left" /> <span className="d-none d-md-inline">Back</span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/excel-file/${excelFileEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" /> <span className="d-none d-md-inline">Edit</span>
        </Button>
      </Col>
    </Row>
  );
};

export default ExcelDataDetail;
