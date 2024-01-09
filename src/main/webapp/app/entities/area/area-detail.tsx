import React, { useEffect } from 'react';
import { Link, useParams } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { Translate } from 'react-jhipster';
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
        <h2 data-cy="areaDetailsHeading">
          <Translate contentKey="dispatchApplicationApp.area.detail.title">Area</Translate>
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="id">
              <Translate contentKey="global.field.id">ID</Translate>
            </span>
          </dt>
          <dd>{areaEntity.id}</dd>
          <dt>
            <span id="name">
              <Translate contentKey="dispatchApplicationApp.area.name">Name</Translate>
            </span>
          </dt>
          <dd>{areaEntity.name}</dd>
          <dt>
            <span id="code">
              <Translate contentKey="dispatchApplicationApp.area.code">Code</Translate>
            </span>
          </dt>
          <dd>{areaEntity.code}</dd>
          <dt>
            <span id="detail">
              <Translate contentKey="dispatchApplicationApp.area.detail">Detail</Translate>
            </span>
          </dt>
          <dd>{areaEntity.detail}</dd>
          <dt>
            <span id="status">
              <Translate contentKey="dispatchApplicationApp.area.status">Status</Translate>
            </span>
          </dt>
          <dd>{areaEntity.status}</dd>
          <dt>
            <Translate contentKey="dispatchApplicationApp.area.olt">Olt</Translate>
          </dt>
          <dd>{areaEntity.olt ? areaEntity.olt.id : ''}</dd>
        </dl>
        <Button tag={Link} to="/area" replace color="info" data-cy="entityDetailsBackButton">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/area/${areaEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

export default AreaDetail;
