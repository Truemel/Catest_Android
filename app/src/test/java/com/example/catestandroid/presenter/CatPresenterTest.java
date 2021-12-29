package com.example.catestandroid.presenter;

import static org.mockito.Mockito.verify;

import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class CatPresenterTest {

    private CatPresenter presenter;

    @Before
    public void init(){
        presenter = Mockito.mock(CatPresenter.class);
    }

    @Test
    @DisplayName("Breed List Request Test")
    public void breedListRequestTest(){
        presenter.breedListRequest(10, 0);
        verify(presenter).breedListRequest(10, 0);
    }

    @Test
    @DisplayName("Cat Request Test")
    public void catRequestTest(){
        String id = "abys";
        int position = 0;
        presenter.catRequest(id, position, null);
        verify(presenter).catRequest(id, position, null);
    }
}
