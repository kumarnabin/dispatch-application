import React, { useEffect } from 'react';
import { Link, useParams } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import {} from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { useAppDispatch, useAppSelector } from 'app/config/store';

import { getEntity } from './area.reducer';

export const AreaDetail = () => {
  const dispatch = useAppDispatch();

  const { id } = useParams<'id'>();

  useEffect(() => {
    dispatch(getEntity(id));
  }, []);

  const areaEntity = useAppSelector(state => state.area.entity);
  return (
    <Row>
      <Col md="8">
        <h2 data-cy="areaDetailsHeading">Translation missing for dispatchApplicationApp.area.detail.title</h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="id">ID</span>
          </dt>
          <dd>{areaEntity.id}</dd>
          <dt>
            <span id="name">Name</span>
          </dt>
          <dd>{areaEntity.name}</dd>
          <dt>
            <span id="code">Code</span>
          </dt>
          <dd>{areaEntity.code}</dd>
          <dt>
            <span id="detail">Detail</span>
          </dt>
          <dd>{areaEntity.detail}</dd>
          <dt>
            <span id="status">Status</span>
          </dt>
          <dd>{areaEntity.status}</dd>
          <dt>Olt</dt>
          <dd>{areaEntity.olt ? areaEntity.olt.id : ''}</dd>
        </dl>
        <Button tag={Link} to="/area" replace color="info" data-cy="entityDetailsBackButton">
          <FontAwesomeIcon icon="arrow-left" /> <span className="d-none d-md-inline">Back</span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/area/${areaEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" /> <span className="d-none d-md-inline">Edit</span>
        </Button>
      </Col>
    </Row>
  );
};

export default AreaDetail;
