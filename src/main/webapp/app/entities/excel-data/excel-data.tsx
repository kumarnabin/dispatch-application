import React, { useState, useEffect } from 'react';
import { Link, useLocation, useNavigate } from 'react-router-dom';
import { Button, Table } from 'reactstrap';
import { Translate, TextFormat, getPaginationState, JhiPagination, JhiItemCount } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faSort, faSortUp, faSortDown } from '@fortawesome/free-solid-svg-icons';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import { ASC, DESC, ITEMS_PER_PAGE, SORT } from 'app/shared/util/pagination.constants';
import { overridePaginationStateWithQueryParams } from 'app/shared/util/entity-utils';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { getEntities } from './excel-data.reducer';

export const ExcelData = () => {
  const dispatch = useAppDispatch();

  const pageLocation = useLocation();
  const navigate = useNavigate();

  const [paginationState, setPaginationState] = useState(
    overridePaginationStateWithQueryParams(getPaginationState(pageLocation, ITEMS_PER_PAGE, 'id'), pageLocation.search),
  );

  const excelDataList = useAppSelector(state => state.excelData.entities);
  const loading = useAppSelector(state => state.excelData.loading);
  const totalItems = useAppSelector(state => state.excelData.totalItems);

  const getAllEntities = () => {
    dispatch(
      getEntities({
        page: paginationState.activePage - 1,
        size: paginationState.itemsPerPage,
        sort: `${paginationState.sort},${paginationState.order}`,
      }),
    );
  };

  const sortEntities = () => {
    getAllEntities();
    const endURL = `?page=${paginationState.activePage}&sort=${paginationState.sort},${paginationState.order}`;
    if (pageLocation.search !== endURL) {
      navigate(`${pageLocation.pathname}${endURL}`);
    }
  };

  useEffect(() => {
    sortEntities();
  }, [paginationState.activePage, paginationState.order, paginationState.sort]);

  useEffect(() => {
    const params = new URLSearchParams(pageLocation.search);
    const page = params.get('page');
    const sort = params.get(SORT);
    if (page && sort) {
      const sortSplit = sort.split(',');
      setPaginationState({
        ...paginationState,
        activePage: +page,
        sort: sortSplit[0],
        order: sortSplit[1],
      });
    }
  }, [pageLocation.search]);

  const sort = p => () => {
    setPaginationState({
      ...paginationState,
      order: paginationState.order === ASC ? DESC : ASC,
      sort: p,
    });
  };

  const handlePagination = currentPage =>
    setPaginationState({
      ...paginationState,
      activePage: currentPage,
    });

  const handleSyncList = () => {
    sortEntities();
  };

  const getSortIconByFieldName = (fieldName: string) => {
    const sortFieldName = paginationState.sort;
    const order = paginationState.order;
    if (sortFieldName !== fieldName) {
      return faSort;
    } else {
      return order === ASC ? faSortUp : faSortDown;
    }
  };

  return (
    <div>
      <h2 id="excel-data-heading" data-cy="ExcelDataHeading">
        Excel Data
        <div className="d-flex justify-content-end">
          <Button className="me-2" color="info" onClick={handleSyncList} disabled={loading}>
            <FontAwesomeIcon icon="sync" spin={loading} /> Refresh list
          </Button>
          <Link to="/excel-data/new" className="btn btn-primary jh-create-entity" id="jh-create-entity" data-cy="entityCreateButton">
            <FontAwesomeIcon icon="plus" />
            &nbsp; Create a new Excel Data
          </Link>
        </div>
      </h2>
      <div className="table-responsive">
        {excelDataList && excelDataList.length > 0 ? (
          <Table responsive>
            <thead>
              <tr>
                <th className="hand" onClick={sort('id')}>
                  ID <FontAwesomeIcon icon={getSortIconByFieldName('id')} />
                </th>
                <th className="hand" onClick={sort('column1')}>
                  Column 1 <FontAwesomeIcon icon={getSortIconByFieldName('column1')} />
                </th>
                <th className="hand" onClick={sort('column2')}>
                  Column 2 <FontAwesomeIcon icon={getSortIconByFieldName('column2')} />
                </th>
                <th className="hand" onClick={sort('column3')}>
                  Column 3 <FontAwesomeIcon icon={getSortIconByFieldName('column3')} />
                </th>
                <th className="hand" onClick={sort('column4')}>
                  Column 4 <FontAwesomeIcon icon={getSortIconByFieldName('column4')} />
                </th>
                <th className="hand" onClick={sort('column5')}>
                  Column 5 <FontAwesomeIcon icon={getSortIconByFieldName('column5')} />
                </th>
                <th className="hand" onClick={sort('column6')}>
                  Column 6 <FontAwesomeIcon icon={getSortIconByFieldName('column6')} />
                </th>
                <th className="hand" onClick={sort('column7')}>
                  Column 7 <FontAwesomeIcon icon={getSortIconByFieldName('column7')} />
                </th>
                <th className="hand" onClick={sort('column8')}>
                  Column 8 <FontAwesomeIcon icon={getSortIconByFieldName('column8')} />
                </th>
                <th className="hand" onClick={sort('column9')}>
                  Column 9 <FontAwesomeIcon icon={getSortIconByFieldName('column9')} />
                </th>
                <th className="hand" onClick={sort('column10')}>
                  Column 10 <FontAwesomeIcon icon={getSortIconByFieldName('column10')} />
                </th>
                <th className="hand" onClick={sort('column11')}>
                  Column 11 <FontAwesomeIcon icon={getSortIconByFieldName('column11')} />
                </th>
                <th className="hand" onClick={sort('column12')}>
                  Column 12 <FontAwesomeIcon icon={getSortIconByFieldName('column12')} />
                </th>
                <th className="hand" onClick={sort('column13')}>
                  Column 13 <FontAwesomeIcon icon={getSortIconByFieldName('column13')} />
                </th>
                <th className="hand" onClick={sort('column14')}>
                  Column 14 <FontAwesomeIcon icon={getSortIconByFieldName('column14')} />
                </th>
                <th className="hand" onClick={sort('column15')}>
                  Column 15 <FontAwesomeIcon icon={getSortIconByFieldName('column15')} />
                </th>
                <th className="hand" onClick={sort('column16')}>
                  Column 16 <FontAwesomeIcon icon={getSortIconByFieldName('column16')} />
                </th>
                <th className="hand" onClick={sort('column17')}>
                  Column 17 <FontAwesomeIcon icon={getSortIconByFieldName('column17')} />
                </th>
                <th className="hand" onClick={sort('column18')}>
                  Column 18 <FontAwesomeIcon icon={getSortIconByFieldName('column18')} />
                </th>
                <th className="hand" onClick={sort('column19')}>
                  Column 19 <FontAwesomeIcon icon={getSortIconByFieldName('column19')} />
                </th>
                <th className="hand" onClick={sort('column20')}>
                  Column 20 <FontAwesomeIcon icon={getSortIconByFieldName('column20')} />
                </th>
                <th className="hand" onClick={sort('column21')}>
                  Column 21 <FontAwesomeIcon icon={getSortIconByFieldName('column21')} />
                </th>
                <th className="hand" onClick={sort('column22')}>
                  Column 22 <FontAwesomeIcon icon={getSortIconByFieldName('column22')} />
                </th>
                <th className="hand" onClick={sort('column23')}>
                  Column 23 <FontAwesomeIcon icon={getSortIconByFieldName('column23')} />
                </th>
                <th className="hand" onClick={sort('column24')}>
                  Column 24 <FontAwesomeIcon icon={getSortIconByFieldName('column24')} />
                </th>
                <th className="hand" onClick={sort('column25')}>
                  Column 25 <FontAwesomeIcon icon={getSortIconByFieldName('column25')} />
                </th>
                <th className="hand" onClick={sort('column26')}>
                  Column 26 <FontAwesomeIcon icon={getSortIconByFieldName('column26')} />
                </th>
                <th className="hand" onClick={sort('column27')}>
                  Column 27 <FontAwesomeIcon icon={getSortIconByFieldName('column27')} />
                </th>
                <th className="hand" onClick={sort('publicationDate')}>
                  Publication Date <FontAwesomeIcon icon={getSortIconByFieldName('publicationDate')} />
                </th>
                <th />
              </tr>
            </thead>
            <tbody>
              {excelDataList.map((excelData, i) => (
                <tr key={`entity-${i}`} data-cy="entityTable">
                  <td>
                    <Button tag={Link} to={`/excel-data/${excelData.id}`} color="link" size="sm">
                      {excelData.id}
                    </Button>
                  </td>
                  <td>{excelData.column1}</td>
                  <td>{excelData.column2}</td>
                  <td>{excelData.column3}</td>
                  <td>{excelData.column4}</td>
                  <td>{excelData.column5}</td>
                  <td>{excelData.column6}</td>
                  <td>{excelData.column7}</td>
                  <td>{excelData.column8}</td>
                  <td>{excelData.column9}</td>
                  <td>{excelData.column10}</td>
                  <td>{excelData.column11}</td>
                  <td>{excelData.column12}</td>
                  <td>{excelData.column13}</td>
                  <td>{excelData.column14}</td>
                  <td>{excelData.column15}</td>
                  <td>{excelData.column16}</td>
                  <td>{excelData.column17}</td>
                  <td>{excelData.column18}</td>
                  <td>{excelData.column19}</td>
                  <td>{excelData.column20}</td>
                  <td>{excelData.column21}</td>
                  <td>{excelData.column22}</td>
                  <td>{excelData.column23}</td>
                  <td>{excelData.column24}</td>
                  <td>{excelData.column25}</td>
                  <td>{excelData.column26}</td>
                  <td>{excelData.column27}</td>
                  <td>
                    {excelData.publicationDate ? (
                      <TextFormat type="date" value={excelData.publicationDate} format={APP_DATE_FORMAT} />
                    ) : null}
                  </td>
                  <td className="text-end">
                    <div className="btn-group flex-btn-group-container">
                      <Button tag={Link} to={`/excel-data/${excelData.id}`} color="info" size="sm" data-cy="entityDetailsButton">
                        <FontAwesomeIcon icon="eye" /> <span className="d-none d-md-inline">View</span>
                      </Button>
                      <Button
                        tag={Link}
                        to={`/excel-data/${excelData.id}/edit?page=${paginationState.activePage}&sort=${paginationState.sort},${paginationState.order}`}
                        color="primary"
                        size="sm"
                        data-cy="entityEditButton"
                      >
                        <FontAwesomeIcon icon="pencil-alt" /> <span className="d-none d-md-inline">Edit</span>
                      </Button>
                      <Button
                        onClick={() =>
                          (window.location.href = `/excel-data/${excelData.id}/delete?page=${paginationState.activePage}&sort=${paginationState.sort},${paginationState.order}`)
                        }
                        color="danger"
                        size="sm"
                        data-cy="entityDeleteButton"
                      >
                        <FontAwesomeIcon icon="trash" /> <span className="d-none d-md-inline">Delete</span>
                      </Button>
                    </div>
                  </td>
                </tr>
              ))}
            </tbody>
          </Table>
        ) : (
          !loading && <div className="alert alert-warning">No Excel Data found</div>
        )}
      </div>
      {totalItems ? (
        <div className={excelDataList && excelDataList.length > 0 ? '' : 'd-none'}>
          <div className="justify-content-center d-flex">
            <JhiItemCount page={paginationState.activePage} total={totalItems} itemsPerPage={paginationState.itemsPerPage} />
          </div>
          <div className="justify-content-center d-flex">
            <JhiPagination
              activePage={paginationState.activePage}
              onSelect={handlePagination}
              maxButtons={5}
              itemsPerPage={paginationState.itemsPerPage}
              totalItems={totalItems}
            />
          </div>
        </div>
      ) : (
        ''
      )}
    </div>
  );
};

export default ExcelData;
